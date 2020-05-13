package lutrom

import chisel3._

class LUTScale extends Bundle {
   val a = UInt(32.W)
   val b = UInt(32.W)
}
