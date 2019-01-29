package lutrom

import Chisel._
import freechips.rocketchip.tile._
import freechips.rocketchip.config._

import lutrom._

class  LUTROMAccelerator(implicit p: Parameters) extends LazyRoCC {
  override lazy val module = new LUTROMAcceleratorModule(this)
}

class LUTROMAcceleratorModule(outer: LUTROMAccelerator, n: Int = 4)(implicit p: Parameters) extends LazyRoCCModule(outer)
  with HasCoreParameters {

  val busy = Reg(init = Vec.fill(n){Bool(false)})

  val cmd = Queue(io.cmd)
  val funct = cmd.bits.inst.funct
  val doLUTOffset = funct === UInt(0)
  val doLUTSlope = funct === UInt(1)

  val LUT = Module(new LUT_ROM())
  

  // datapath
  //val vmem = Wire(UInt("h41200000",32)) //Wire(UInt(0,32))
  //val curveSelect = Wire(UInt(28,32)) //Wire(UInt(0,32))
  val result = Wire(UInt(0,32)) //RegInit(0.U(32.W))

 // val oldVmem = RegInit("h41200000".U(32.W)) //Wire(UInt("h41200000",32))
 // val oldCurveSelect = RegInit(28.U(32.W)) //Wire(UInt(28,32))
 // val oldResult = RegInit(0.U(32.W)) //Wire(UInt(0,32))

  val offset = Wire(UInt(0,32))
  val slope = Wire(UInt(0,32))

  val ready = RegInit(0.U(1.W))//(UInt(0,1))
  ready := (LUT.io.ready)

  when(cmd.fire()){
      LUT.io.Vmem := cmd.bits.rs1
      LUT.io.curveSelect := cmd.bits.rs2
      LUT.io.doLUT := 1.U
  }
  
  when (io.resp.fire()) {
    LUT.io.doLUT := 0.U
  }
  // control

  val doResp = cmd.bits.inst.xd
  val stallResp = doResp && !io.resp.ready

  cmd.ready := !stallResp//&& (state === s_idle)
    // command resolved if no stalls AND not issuing a load that will need a request

  // PROC RESPONSE INTERFACE
  io.resp.valid := cmd.valid && doResp && (ready === 1.U)
    // valid response if valid command, need a response, and no stalls
  io.resp.bits.rd := cmd.bits.inst.rd
    // Must respond with the appropriate tag or undefined behavior
  io.resp.bits.data := Mux(funct === UInt(0),LUT.io.offset,LUT.io.slope)
    // Semantics is to always send out prior accumulator register value

  io.busy := cmd.valid || busy.reduce(_||_)
    // Be busy when have pending memory requests or committed possibility of pending requests
  io.interrupt := Bool(false)
    // Set this true to trigger an interrupt on the processor (please refer to supervisor documentation)
  io.mem.req.valid := Bool(false)

}
