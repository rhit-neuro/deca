// See LICENSE.SiFive for license details.
// See LICENSE.Berkeley for license details.

package freechips.rocketchip.system

import Chisel._
//import freechips.rocketchip.config.Config
import freechips.rocketchip.subsystem._
//import freechips.rocketchip.devices.debug.{IncludeJtagDTM, JtagDTMKey}
import freechips.rocketchip.diplomacy._
import freechips.rocketchip.system._

import freechips.rocketchip.config._
import freechips.rocketchip.devices.debug._
import freechips.rocketchip.devices.tilelink._
import freechips.rocketchip.rocket._
import freechips.rocketchip.tile._
import freechips.rocketchip.tilelink._
import freechips.rocketchip.util._

//import freechips.rocketchip.util.InOrderArbiter


class CustomAcceleratorConfig extends Config(
  new WithCustomAccelerator ++ new DefaultConfig)


class WithCustomAccelerator extends Config((site, here, up) => {
      case RocketTilesKey => up(RocketTilesKey, site).map { r =>
        r.copy(rocc = Seq(
          RoCCParams(
            opcodes = OpcodeSet.custom0,
            generator = (p: Parameters) => {
              val multiplier = LazyModule(new MultAccelerator()(p))
              multiplier})
          ))
      }
})

class  MultAccelerator(implicit p: Parameters) extends LazyRoCC {
  override lazy val module = new MultAcceleratorModule(this)
}

class MultAcceleratorModule(outer: MultAccelerator, n: Int = 4)(implicit p: Parameters) extends LazyRoCCModule(outer)
  with HasCoreParameters {

  val busy = Reg(init = Vec.fill(n){Bool(false)})

  val cmd = Queue(io.cmd)
  val funct = cmd.bits.inst.funct
  val doMult = funct === UInt(0)

  // datapath
  val one = Reg(UInt(width = 16))
  val two = Reg(UInt(width = 16))

  val result = Reg(UInt(width = 16))

  when (cmd.fire() && doMult) {
      one := cmd.bits.rs1
      two := cmd.bits.rs2

      result := cmd.bits.rs1
  }

  // control

  val doResp = cmd.bits.inst.xd
  val stallResp = doResp && !io.resp.ready

  cmd.ready := !stallResp
    // command resolved if no stalls AND not issuing a load that will need a request

  // PROC RESPONSE INTERFACE
  io.resp.valid := cmd.valid && doResp
    // valid response if valid command, need a response, and no stalls
  io.resp.bits.rd := cmd.bits.inst.rd
    // Must respond with the appropriate tag or undefined behavior
  io.resp.bits.data := cmd.bits.rs1 * cmd.bits.rs2
    // Semantics is to always send out prior accumulator register value

  io.busy := cmd.valid || busy.reduce(_||_)
    // Be busy when have pending memory requests or committed possibility of pending requests
  io.interrupt := Bool(false)
    // Set this true to trigger an interrupt on the processor (please refer to supervisor documentation)
  io.mem.req.valid := Bool(false)

}

