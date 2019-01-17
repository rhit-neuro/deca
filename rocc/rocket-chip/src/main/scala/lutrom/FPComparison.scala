package lutrom

import chisel3._

class FPGreaterThan extends Module{
   val io = IO(new Bundle {
           val greater = Input(UInt(32.W))
           val lesser = Input(UInt(32.W))
           val greaterThan = Output(UInt(1.W))
   })

   val comparison = Reg(UInt(1.W))
   comparison := io.greater >= io.lesser
   val bitSame = Reg(UInt(1.W))
   bitSame := io.greater(31) === io.lesser(31)
   val sign = Reg(UInt(1.W))
   sign := io.greater(31)
   io.greaterThan := (comparison & bitSame & !sign) | (!comparison & !bitSame) | (!comparison & bitSame & sign) 
  
} 
