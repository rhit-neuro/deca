package lutrom

import chisel3._

class LUTScalesTable2 extends Module {
    val io = IO(new Bundle {
        val scale14 = Output(new LUTScale())
        val scale15 = Output(new LUTScale())
        val scale16 = Output(new LUTScale())
        val scale17 = Output(new LUTScale())
        val scale18 = Output(new LUTScale())
        val scale19 = Output(new LUTScale())
        val scale20 = Output(new LUTScale())
        val scale21 = Output(new LUTScale())
        val scale22 = Output(new LUTScale())
        val scale23 = Output(new LUTScale())
        val scale24 = Output(new LUTScale())
        val scale25 = Output(new LUTScale())
        val scale26 = Output(new LUTScale())
        val scale27 = Output(new LUTScale())
        val scale28 = Output(new LUTScale())
    })

    io.scale14.a := "hc3c80000".U(32.W)
    io.scale14.b := "hc1b66666".U(32.W)
    io.scale15.a := "hc3fa0000".U(32.W)
    io.scale15.b := "hc1600000".U(32.W)
    io.scale16.a := "hc3870000".U(32.W)
    io.scale16.b := "hc16d999a".U(32.W)
    io.scale17.a := "h43c80000".U(32.W)
    io.scale17.b := "h419bd70a".U(32.W)
    io.scale18.a := "h437a0000".U(32.W)
    io.scale18.b := "h412c0000".U(32.W)
    io.scale19.a := "hc3160000".U(32.W)
    io.scale19.b := "hc019999a".U(32.W)
    io.scale20.a := "h430f0000".U(32.W)
    io.scale20.b := "h3fedf3b7".U(32.W)
    io.scale21.a := "hc3480000".U(32.W)
    io.scale21.b := "hc0c00000".U(32.W)
    io.scale22.a := "h43960000".U(32.W)
    io.scale22.b := "h41840000".U(32.W)
    io.scale23.a := "h42c80000".U(32.W)
    io.scale23.b := "h400ccccd".U(32.W)
    io.scale24.a := "h42c80000".U(32.W)
    io.scale24.b := "h40e99999".U(32.W)
    io.scale25.a := "h43960000".U(32.W)
    io.scale25.b := "h4101999a".U(32.W)
    io.scale26.a := "h42c80000".U(32.W)
    io.scale26.b := "h40800000".U(32.W)
    io.scale27.a := "hc3a50000".U(32.W)
    io.scale27.b := "hc1769375".U(32.W)
    io.scale28.a := "h43340000".U(32.W)
    io.scale28.b := "h41075c29".U(32.W)

}
