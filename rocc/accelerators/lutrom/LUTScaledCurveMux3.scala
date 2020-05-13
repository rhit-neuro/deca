package lutrom

import chisel3._
import chisel3.util._
import lutrom._

class LUTScaledCurveMux3 extends Module{
   val io = IO(new Bundle {
           val curveSelect = Input(UInt(32.W))
           val curve0 = Input(new LUTCurve())
           val curve1 = Input(new LUTCurve())
           val curve2 = Input(new LUTCurve())
           val curveOut = Output(new LUTCurve()) 
   })

   when(io.curveSelect === 28.U) {
      io.curveOut := io.curve1
   } .elsewhen(io.curveSelect > 24.U) {
      io.curveOut := io.curve2
   } .otherwise {
      io.curveOut := io.curve0
   }
}
