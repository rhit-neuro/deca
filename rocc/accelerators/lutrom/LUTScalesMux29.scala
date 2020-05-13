package lutrom

import chisel3._
import chisel3.util._
import lutrom._

class LUTScalesMux29 extends Module{
   val io = IO(new Bundle {
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
           val scaleOut = Output(new LUTScale()) 
   })

   io.scaleOut := io.scale0

   switch(io.scaleSelect){
      is(0.U){
        io.scaleOut := io.scale0 
      }
      is(1.U){
        io.scaleOut := io.scale1
      }
      is(2.U){
        io.scaleOut := io.scale2
      }
      is(3.U){
        io.scaleOut := io.scale3
      }
      is(4.U){
        io.scaleOut := io.scale4
      }
      is(5.U){
        io.scaleOut := io.scale5
      }
      is(6.U){
        io.scaleOut := io.scale6
      }
      is(7.U){
        io.scaleOut := io.scale7
      }
      is(8.U){
        io.scaleOut := io.scale8
      }
      is(9.U){
        io.scaleOut := io.scale9
      }
      is(10.U){
        io.scaleOut := io.scale10
      }
      is(11.U){
        io.scaleOut := io.scale11
      }
      is(12.U){
        io.scaleOut := io.scale12
      }
      is(13.U){
        io.scaleOut := io.scale13
      }
      is(14.U){
        io.scaleOut := io.scale14
      }
      is(15.U){
        io.scaleOut := io.scale15
      }
      is(16.U){
        io.scaleOut := io.scale16
      }
      is(17.U){
        io.scaleOut := io.scale17
      }
      is(18.U){
        io.scaleOut := io.scale18
      }
      is(19.U){
        io.scaleOut := io.scale19
      }
      is(20.U){
        io.scaleOut := io.scale20
      }
      is(21.U){
        io.scaleOut := io.scale21
      }
      is(22.U){
        io.scaleOut := io.scale22
      }
      is(23.U){
        io.scaleOut := io.scale23
      }
      is(24.U){
        io.scaleOut := io.scale24
      }
      is(25.U){
        io.scaleOut := io.scale25
      }
      is(26.U){
        io.scaleOut := io.scale26
      }
      is(27.U){
        io.scaleOut := io.scale27
      }
      is(28.U){
        io.scaleOut := io.scale28
      }
   }
  
} 
