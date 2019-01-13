package mult

import chisel3._

class VOSBundle extends Bundle {
   val Vmem = Vec(32, UInt(32.W))
   val offset = Vec(32, UInt(32.W))
   val slope = Vec(32, UInt(32.W))
}

class LUT_ROM extends Module{
   val io = IO(new Bundle {
           val Vmem = Input(UInt(32.W))
           val curveSelect = Input(UInt(5.W))
           val slope = Output(UInt(32.W))
           val offset = Output(UInt(32.W))
   })

   val one = Wire(new VOSBundle)
   one.Vmem := VecInit(Array(0.U,1.U,2.U,3.U,4.U,5.U,6.U,7.U,8.U,9.U,10.U,11.U,12.U,13.U,14.U,15.U,16.U,17.U,18.U,19.U,20.U,21.U,22.U,23.U,24.U,25.U,26.U,27.U,28.U,29.U,30.U,31.U))

   one.offset := VecInit(Array(0.U,1.U,2.U,3.U,4.U,5.U,6.U,7.U,8.U,9.U,10.U,11.U,12.U,13.U,14.U,15.U,16.U,17.U,18.U,19.U,20.U,21.U,22.U,23.U,24.U,25.U,26.U,27.U,28.U,29.U,30.U,31.U))

   one.slope := VecInit(Array(0.U,1.U,2.U,3.U,4.U,5.U,6.U,7.U,8.U,9.U,10.U,11.U,12.U,13.U,14.U,15.U,16.U,17.U,18.U,19.U,20.U,21.U,22.U,23.U,24.U,25.U,26.U,27.U,28.U,29.U,30.U,31.U))

   io.slope := one.offset(io.curveSelect)
   io.offset := one.slope(io.curveSelect)

}
