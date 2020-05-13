package lutrom

import chisel3._
import chisel3.util._
import lutrom._

class LUTStateScalesMux29 extends Module{
   val io = IO(new Bundle {
      val req = Flipped(Decoupled(new Bundle {
           val scaleSelect = Input(UInt(32.W))
           val scale0 = Input(new LUTScale())
           val scale1 = Input(new LUTScale())
           val scale2 = Input(new LUTScale())
           val scale3 = Input(new LUTScale())
           val scale4 = Input(new LUTScale())
           val scale5 = Input(new LUTScale())
           val scale6 = Input(new LUTScale())
           val scale7 = Input(new LUTScale())
           val scale8 = Input(new LUTScale())
           val scale9 = Input(new LUTScale())
           val scale10 = Input(new LUTScale())
           val scale11 = Input(new LUTScale())
           val scale12 = Input(new LUTScale())
           val scale13 = Input(new LUTScale())
           val scale14 = Input(new LUTScale())
           val scale15 = Input(new LUTScale())
           val scale16 = Input(new LUTScale())
           val scale17 = Input(new LUTScale())
           val scale18 = Input(new LUTScale())
           val scale19 = Input(new LUTScale())
           val scale20 = Input(new LUTScale())
           val scale21 = Input(new LUTScale())
           val scale22 = Input(new LUTScale())
           val scale23 = Input(new LUTScale())
           val scale24 = Input(new LUTScale())
           val scale25 = Input(new LUTScale())
           val scale26 = Input(new LUTScale())
           val scale27 = Input(new LUTScale())
           val scale28 = Input(new LUTScale())
      }))
      val resp = Decoupled(new Bundle {
           val scaleOut = Output(new LUTScale()) 
      })
   })

  val old_scaleSelect = RegInit(0.U(32.W))
  val old_scaleOut = Reg(new LUTScale())

  val res = RegInit(io.req.bits.scale3) //new LUTScale())

  val s_idle :: s_scale :: s_set_o_s :: s_resp :: Nil = Enum(4)
  val state = RegInit(s_idle)

  io.req.ready := (state === s_idle)

  io.resp.valid := (state === s_resp)
  io.resp.bits.scaleOut := old_scaleOut



  when (io.req.fire()) {
    state := s_scale
  }

  when (state === s_scale) {
    res := io.req.bits.scale4

    switch(io.req.bits.scaleSelect){
      is(0.U){
        res := io.req.bits.scale0 
      }
      is(1.U){
        res := io.req.bits.scale1
      }
      is(2.U){
        res := io.req.bits.scale2
      }
      is(3.U){
        res := io.req.bits.scale3
      }
      is(4.U){
        res := io.req.bits.scale4
      }
      is(5.U){
        res := io.req.bits.scale5
      }
      is(6.U){
        res := io.req.bits.scale6
      }
      is(7.U){
        res := io.req.bits.scale7
      }
      is(8.U){
        res := io.req.bits.scale8
      }
      is(9.U){
        res := io.req.bits.scale9
      }
      is(10.U){
        res := io.req.bits.scale10
      }
      is(11.U){
        res := io.req.bits.scale11
      }
      is(12.U){
        res := io.req.bits.scale12
      }
      is(13.U){
        res := io.req.bits.scale13
      }
      is(14.U){
        res := io.req.bits.scale14
      }
      is(15.U){
        res := io.req.bits.scale15
      }
      is(16.U){
        res := io.req.bits.scale16
      }
      is(17.U){
        res := io.req.bits.scale17
      }
      is(18.U){
        res := io.req.bits.scale18
      }
      is(19.U){
        res := io.req.bits.scale19
      }
      is(20.U){
        res := io.req.bits.scale20
      }
      is(21.U){
        res := io.req.bits.scale21
      }
      is(22.U){
        res := io.req.bits.scale22
      }
      is(23.U){
        res := io.req.bits.scale23
      }
      is(24.U){
        res := io.req.bits.scale24
      }
      is(25.U){
        res := io.req.bits.scale25
      }
      is(26.U){
        res := io.req.bits.scale26
      }
      is(27.U){
        res := io.req.bits.scale27
      }
      is(28.U){
        res := io.req.bits.scale28
      }
    }

    state := s_set_o_s
  }

  when (state === s_set_o_s) {
    old_scaleOut := res
    old_scaleSelect := io.req.bits.scaleSelect

    state := s_resp
  }

  when (io.resp.fire()) {
    state := s_idle
  }
  
} 
