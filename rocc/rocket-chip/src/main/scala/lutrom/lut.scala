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
  //val vmem = Reg(UInt(width = 32)) 
  val vmem = RegInit(0.U(32.W))

  //val curveSelect = Reg(UInt(width = 5))
  val curveSelect = RegInit(0.U(32.W))

  //val result = Reg(UInt(width = 32))
  val result = RegInit(0.U(32.W))

  //val offset = Reg(UInt(width = 32))
  val offset = RegInit(0.U(32.W))

  //val slope = Reg(UInt(width = 32))
  val slope = RegInit(0.U(32.W))

 // val s_idle :: s_lut_req :: s_lut_resp :: s_resp :: Nil = Enum(Bits(),4)
  //val state = Reg(init = s_idle)

  when (cmd.fire() && doLUTOffset) {
      vmem := cmd.bits.rs1
      curveSelect := cmd.bits.rs2

   //   state := s_lut_req
      LUT.io.Vmem := vmem
      LUT.io.curveSelect := curveSelect

      offset := LUT.io.offset
      slope := LUT.io.slope
      result := offset
  }


  when (cmd.fire() && doLUTSlope){
      vmem := cmd.bits.rs1
      curveSelect := cmd.bits.rs2

      LUT.io.Vmem := vmem
      LUT.io.curveSelect := curveSelect

      offset := LUT.io.offset
      slope := LUT.io.slope

      result := slope
  }
  //when (cmd.fire() && doLUTSlope) {
  //    state := s_resp
  //}

  //when (state === s_lut_req) {
  //    state := s_lut_resp
  //}

  //when (state === s_lut_resp) {
  //    offset := LUT.io.offset
  //    slope := LUT.io.slope
  //    when(doLUTOffset){
  //        result := offset
  //    }
  //    when(doLUTSlope){
  //        result := slope
  //    }
  //    state := s_resp
  //}

  //when (io.resp.fire()) { state := s_idle }

  // control

  val doResp = cmd.bits.inst.xd
  val stallResp = doResp && !io.resp.ready

  cmd.ready := !stallResp //&& (state === s_idle)
    // command resolved if no stalls AND not issuing a load that will need a request

  // PROC RESPONSE INTERFACE
  io.resp.valid := cmd.valid && doResp //&& (state === s_resp)
    // valid response if valid command, need a response, and no stalls
  io.resp.bits.rd := cmd.bits.inst.rd
    // Must respond with the appropriate tag or undefined behavior
  io.resp.bits.data := result
    // Semantics is to always send out prior accumulator register value

  io.busy := cmd.valid || busy.reduce(_||_)
    // Be busy when have pending memory requests or committed possibility of pending requests
  io.interrupt := Bool(false)
    // Set this true to trigger an interrupt on the processor (please refer to supervisor documentation)
  io.mem.req.valid := Bool(false)

}