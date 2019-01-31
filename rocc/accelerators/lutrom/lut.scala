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

  val funct = io.cmd.bits.inst.funct
  val do_LUT_offset = (funct === UInt(0))
  val do_LUT_slope = (funct === UInt(1))

  val LUT = Module(new LUT_ROM())
  
  val req_rd = Reg(io.resp.bits.rd)
  // Return variable
  val output = RegInit(0.U(32.W))

  // Setup states
  val s_idle :: s_req_lut :: s_resp_lut :: s_resp :: Nil = Enum(Bits(), 4)
  val state = Reg(init = s_idle)
  
  // datapath

  // When we get a command, start the LUT and
  // move to the s_req_lut state, waiting for the
  // LUT to finish
  when (io.cmd.fire()){
      LUT.io.req.bits.v_mem := io.cmd.bits.rs1
      LUT.io.req.bits.curve_select := io.cmd.bits.rs2
      req_rd := io.cmd.bits.inst.rd
      state := s_req_lut
  }
  
  when (LUT.io.req.fire()) { state := s_req_lut }

  when (state === s_resp_lut && LUT.io.resp.fire()) {
    output := Mux(do_LUT_offset, LUT.io.resp.bits.offset, LUT.io.resp.bits.slope)
    state := s_resp
  }

  when (io.resp.fire()) { state := s_idle }
  
  LUT.io.req.valid := (state === s_req_lut)
  LUT.io.resp.ready := (state === s_resp_lut)

  io.cmd.ready := (state === s_idle)

  io.resp.valid := (state === s_resp)
  io.resp.bits.rd := req_rd
  io.resp.bits.data := output

  io.busy := (state =/= s_idle)
  io.interrupt := Bool(false)
  io.mem.req.valid := Bool(false)
}
