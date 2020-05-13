package lutrom

import chisel3._
import chisel3.util._
import lutrom._

class LUTCurveMux3 extends Module{
   val io = IO(new Bundle {
           val curveSelect = Input(UInt(32.W))
           val curve0 = Input(new LUTCurve())
           val curve1 = Input(new LUTCurve())
           val curve2 = Input(new LUTCurve())
           val curveOut = Output(new LUTCurve()) 
   })

   io.curveOut := io.curve0

   switch(io.curveSelect){
      is(0.U){
        io.curveOut := io.curve0 
      }
      is(1.U){
        io.curveOut := io.curve1
      }
      is(2.U){
        io.curveOut := io.curve2
      }
   }
  
} 