package lutrom

import chisel3._
import chisel3.util._

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

   val comparison = Wire(Vec(32, UInt(1.W)))
   val bundle1 = Wire(new VOSBundle)
   val xorComparison = Wire(Vec(31, UInt(1.W)))

   bundle1.Vmem := VecInit(Array("hbd9f91e6".U(32.W),"hbd879cc7".U(32.W),"hbd75c4a8".U(32.W),"hbd643dcd".U(32.W),"hbd56bfcb".U(32.W),"hbd4bac71".U(32.W),"hbd4230fd".U(32.W),"hbd39ce8e".U(32.W),"hbd323359".U(32.W),"hbd2b26c0".U(32.W),"hbd247cb7".U(32.W),"hbd1e0d67".U(32.W),"hbd17b203".U(32.W),"hbd113f8e".U(32.W),"hbd0a71de".U(32.W),"hbd02b1f7".U(32.W),"hbcd5bccb".U(32.W),"hbcc63cfb".U(32.W),"hbcb89f84".U(32.W),"hbcabba99".U(32.W),"hbc9f05ea".U(32.W),"hbc92274a".U(32.W),"hbc84d120".U(32.W),"hbc6d6fdb".U(32.W),"hbc4f073a".U(32.W),"hbc2d7d7c".U(32.W),"hbc078d93".U(32.W),"hbbb683b5".U(32.W),"hbb152597".U(32.W),"h3b036651".U(32.W),"h3c06a5a1".U(32.W),"h3ca328fa".U(32.W)))
   bundle1.offset := VecInit(Array("h3cae642c".U(32.W),"h3d84790c".U(32.W),"h3e01dd1a".U(32.W),"h3e5202da".U(32.W),"h3e983559".U(32.W),"h3ecd7710".U(32.W),"h3f03de2f".U(32.W),"h3f22fa1e".U(32.W),"h3f437ef6".U(32.W),"h3f64d63d".U(32.W),"h3f833198".U(32.W),"h3f93b35b".U(32.W),"h3fa38cb9".U(32.W),"h3fb23e29".U(32.W),"h3fbf2043".U(32.W),"h3fc907fb".U(32.W),"h3fc0ed1c".U(32.W),"h3fb7b2ff".U(32.W),"h3fae5bc8".U(32.W),"h3fa5636f".U(32.W),"h3f9d151e".U(32.W),"h3f95a262".U(32.W),"h3f8f2a0f".U(32.W),"h3f89bfdf".U(32.W),"h3f856af0".U(32.W),"h3f822a1b".U(32.W),"h3f7fe5eb".U(32.W),"h3f7d6473".U(32.W),"h3f7c9321".U(32.W),"h3f7d1b1e".U(32.W),"h3f7e7ba2".U(32.W),"h3f7fd567".U(32.W)))
   bundle1.slope := VecInit(Array("h3e879d0a".U(32.W),"h3f6b9431".U(32.W),"h3ffa57a8".U(32.W),"h405710cb".U(32.W),"h40a3cd36".U(32.W),"h40e6be0e".U(32.W),"h4119c77a".U(32.W),"h4144a3d7".U(32.W),"h41735c29".U(32.W),"h41929db2".U(32.W),"h41acb852".U(32.W),"h41c774bc".U(32.W),"h41e2353f".U(32.W),"h41fc1aa0".U(32.W),"h4209f5c3".U(32.W),"h4213a9fc".U(32.W),"h4209f3b6".U(32.W),"h41fc147b".U(32.W),"h41e22d0e".U(32.W),"h41c76e98".U(32.W),"h41acb021".U(32.W),"h4192978d".U(32.W),"h41734fdf".U(32.W),"h41449ba6".U(32.W),"h4119c083".U(32.W),"h40e6b368".U(32.W),"h40a3c505".U(32.W),"h405706f7".U(32.W),"h3ffa4745".U(32.W),"h3f6b8280".U(32.W),"h3e87952d".U(32.W),"h0".U(32.W)))

   val fpgtEdge = Module(new FPGreaterThan())
   fpgtEdge.io.greater := bundle1.Vmem(0)
   fpgtEdge.io.lesser := io.Vmem


   val fpgt0 = Module(new FPGreaterThan())
   fpgt0.io.greater := io.Vmem
   fpgt0.io.lesser := bundle1.Vmem(0)
   comparison(0) := fpgt0.io.greaterThan

   val fpgt1 = Module(new FPGreaterThan())
   fpgt1.io.greater := io.Vmem
   fpgt1.io.lesser := bundle1.Vmem(1)
   comparison(1) := fpgt1.io.greaterThan

   val fpgt2 = Module(new FPGreaterThan())
   fpgt2.io.greater := io.Vmem
   fpgt2.io.lesser := bundle1.Vmem(2)
   comparison(2) := fpgt2.io.greaterThan

   val fpgt3 = Module(new FPGreaterThan())
   fpgt3.io.greater := io.Vmem
   fpgt3.io.lesser := bundle1.Vmem(3)
   comparison(3) := fpgt3.io.greaterThan

   val fpgt4 = Module(new FPGreaterThan())
   fpgt4.io.greater := io.Vmem
   fpgt4.io.lesser := bundle1.Vmem(4)
   comparison(4) := fpgt4.io.greaterThan

   val fpgt5 = Module(new FPGreaterThan())
   fpgt5.io.greater := io.Vmem
   fpgt5.io.lesser := bundle1.Vmem(5)
   comparison(5) := fpgt5.io.greaterThan

   val fpgt6 = Module(new FPGreaterThan())
   fpgt6.io.greater := io.Vmem
   fpgt6.io.lesser := bundle1.Vmem(6)
   comparison(6) := fpgt6.io.greaterThan

   val fpgt7 = Module(new FPGreaterThan())
   fpgt7.io.greater := io.Vmem
   fpgt7.io.lesser := bundle1.Vmem(7)
   comparison(7) := fpgt7.io.greaterThan

   val fpgt8 = Module(new FPGreaterThan())
   fpgt8.io.greater := io.Vmem
   fpgt8.io.lesser := bundle1.Vmem(8)
   comparison(8) := fpgt8.io.greaterThan

   val fpgt9 = Module(new FPGreaterThan())
   fpgt9.io.greater := io.Vmem
   fpgt9.io.lesser := bundle1.Vmem(9)
   comparison(9) := fpgt9.io.greaterThan

   val fpgt10 = Module(new FPGreaterThan())
   fpgt10.io.greater := io.Vmem
   fpgt10.io.lesser := bundle1.Vmem(10)
   comparison(10) := fpgt10.io.greaterThan

   val fpgt11 = Module(new FPGreaterThan())
   fpgt11.io.greater := io.Vmem
   fpgt11.io.lesser := bundle1.Vmem(11)
   comparison(11) := fpgt11.io.greaterThan

   val fpgt12 = Module(new FPGreaterThan())
   fpgt12.io.greater := io.Vmem
   fpgt12.io.lesser := bundle1.Vmem(12)
   comparison(12) := fpgt12.io.greaterThan

   val fpgt13 = Module(new FPGreaterThan())
   fpgt13.io.greater := io.Vmem
   fpgt13.io.lesser := bundle1.Vmem(13)
   comparison(13) := fpgt13.io.greaterThan

   val fpgt14 = Module(new FPGreaterThan())
   fpgt14.io.greater := io.Vmem
   fpgt14.io.lesser := bundle1.Vmem(14)
   comparison(14) := fpgt14.io.greaterThan

   val fpgt15 = Module(new FPGreaterThan())
   fpgt15.io.greater := io.Vmem
   fpgt15.io.lesser := bundle1.Vmem(15)
   comparison(15) := fpgt15.io.greaterThan

   val fpgt16 = Module(new FPGreaterThan())
   fpgt16.io.greater := io.Vmem
   fpgt16.io.lesser := bundle1.Vmem(16)
   comparison(16) := fpgt16.io.greaterThan

   val fpgt17 = Module(new FPGreaterThan())
   fpgt17.io.greater := io.Vmem
   fpgt17.io.lesser := bundle1.Vmem(17)
   comparison(17) := fpgt17.io.greaterThan

   val fpgt18 = Module(new FPGreaterThan())
   fpgt18.io.greater := io.Vmem
   fpgt18.io.lesser := bundle1.Vmem(18)
   comparison(18) := fpgt18.io.greaterThan

   val fpgt19 = Module(new FPGreaterThan())
   fpgt19.io.greater := io.Vmem
   fpgt19.io.lesser := bundle1.Vmem(19)
   comparison(19) := fpgt19.io.greaterThan

   val fpgt20 = Module(new FPGreaterThan())
   fpgt20.io.greater := io.Vmem
   fpgt20.io.lesser := bundle1.Vmem(20)
   comparison(20) := fpgt20.io.greaterThan

   val fpgt21 = Module(new FPGreaterThan())
   fpgt21.io.greater := io.Vmem
   fpgt21.io.lesser := bundle1.Vmem(21)
   comparison(21) := fpgt21.io.greaterThan

   val fpgt22 = Module(new FPGreaterThan())
   fpgt22.io.greater := io.Vmem
   fpgt22.io.lesser := bundle1.Vmem(22)
   comparison(22) := fpgt22.io.greaterThan

   val fpgt23 = Module(new FPGreaterThan())
   fpgt23.io.greater := io.Vmem
   fpgt23.io.lesser := bundle1.Vmem(23)
   comparison(23) := fpgt23.io.greaterThan

   val fpgt24 = Module(new FPGreaterThan())
   fpgt24.io.greater := io.Vmem
   fpgt24.io.lesser := bundle1.Vmem(24)
   comparison(24) := fpgt24.io.greaterThan

   val fpgt25 = Module(new FPGreaterThan())
   fpgt25.io.greater := io.Vmem
   fpgt25.io.lesser := bundle1.Vmem(25)
   comparison(25) := fpgt25.io.greaterThan

   val fpgt26 = Module(new FPGreaterThan())
   fpgt26.io.greater := io.Vmem
   fpgt26.io.lesser := bundle1.Vmem(26)
   comparison(26) := fpgt26.io.greaterThan

   val fpgt27 = Module(new FPGreaterThan())
   fpgt27.io.greater := io.Vmem
   fpgt27.io.lesser := bundle1.Vmem(27)
   comparison(27) := fpgt27.io.greaterThan

   val fpgt28 = Module(new FPGreaterThan())
   fpgt28.io.greater := io.Vmem
   fpgt28.io.lesser := bundle1.Vmem(28)
   comparison(28) := fpgt28.io.greaterThan

   val fpgt29 = Module(new FPGreaterThan())
   fpgt29.io.greater := io.Vmem
   fpgt29.io.lesser := bundle1.Vmem(29)
   comparison(29) := fpgt29.io.greaterThan

   val fpgt30 = Module(new FPGreaterThan())
   fpgt30.io.greater := io.Vmem
   fpgt30.io.lesser := bundle1.Vmem(30)
   comparison(30) := fpgt30.io.greaterThan

   val fpgt31 = Module(new FPGreaterThan())
   fpgt31.io.greater := io.Vmem
   fpgt31.io.lesser := bundle1.Vmem(31)
   comparison(31) := fpgt31.io.greaterThan

   xorComparison(0) := comparison(0) ^ comparison(1)
   xorComparison(1) := comparison(1) ^ comparison(2)
   xorComparison(2) := comparison(2) ^ comparison(3)
   xorComparison(3) := comparison(3) ^ comparison(4)
   xorComparison(4) := comparison(4) ^ comparison(5)
   xorComparison(5) := comparison(5) ^ comparison(6)
   xorComparison(6) := comparison(6) ^ comparison(7)
   xorComparison(7) := comparison(7) ^ comparison(8)
   xorComparison(8) := comparison(8) ^ comparison(9)
   xorComparison(9) := comparison(9) ^ comparison(10)
   xorComparison(10) := comparison(10) ^ comparison(11)
   xorComparison(11) := comparison(11) ^ comparison(12)
   xorComparison(12) := comparison(12) ^ comparison(13)
   xorComparison(13) := comparison(13) ^ comparison(14)
   xorComparison(14) := comparison(14) ^ comparison(15)
   xorComparison(15) := comparison(15) ^ comparison(16)
   xorComparison(16) := comparison(16) ^ comparison(17)
   xorComparison(17) := comparison(17) ^ comparison(18)
   xorComparison(18) := comparison(18) ^ comparison(19)
   xorComparison(19) := comparison(19) ^ comparison(20)
   xorComparison(20) := comparison(20) ^ comparison(21)
   xorComparison(21) := comparison(21) ^ comparison(22)
   xorComparison(22) := comparison(22) ^ comparison(23)
   xorComparison(23) := comparison(23) ^ comparison(24)
   xorComparison(24) := comparison(24) ^ comparison(25)
   xorComparison(25) := comparison(25) ^ comparison(26)
   xorComparison(26) := comparison(26) ^ comparison(27)
   xorComparison(27) := comparison(27) ^ comparison(28)
   xorComparison(28) := comparison(28) ^ comparison(29)
   xorComparison(29) := comparison(29) ^ comparison(30)
   xorComparison(30) := comparison(30) ^ comparison(31)

   val addrReg = RegInit(0.U(5.W))
   switch(xorComparison.asUInt){
		is(0.U){
			when(fpgtEdge.io.greaterThan === 1.U){
				addrReg := 0.U
			}.otherwise{
				addrReg := 31.U
			}
		}
		is(1.U){
			addrReg := 0.U
		}
		is(2.U){
			addrReg := 1.U
		}
		is(4.U){
			addrReg := 2.U
		}
		is(8.U){
			addrReg := 3.U
		}
		is(16.U){
			addrReg := 4.U
		}
		is(32.U){
			addrReg := 5.U
		}
		is(64.U){
			addrReg := 6.U
		}
		is(128.U){
			addrReg := 7.U
		}
		is(256.U){
			addrReg := 8.U
		}
		is(512.U){
			addrReg := 9.U
		}
		is(1024.U){
			addrReg := 10.U
		}
		is(2048.U){
			addrReg := 11.U
		}
		is(4096.U){
			addrReg := 12.U
		}
		is(8192.U){
			addrReg := 13.U
		}
		is(16384.U){
			addrReg := 14.U
		}
		is(32768.U){
			addrReg := 15.U
		}
		is(65536.U){
			addrReg := 16.U
		}
		is(131072.U){
			addrReg := 17.U
		}
		is(262144.U){
			addrReg := 18.U
		}
		is(524288.U){
			addrReg := 19.U
		}
		is(1048576.U){
			addrReg := 20.U
		}
		is(2097152.U){
			addrReg := 21.U
		}
		is(4194304.U){
			addrReg := 22.U
		}
		is(8388608.U){
			addrReg := 23.U
		}
		is(16777216.U){
			addrReg := 24.U
		}
		is(33554432.U){
			addrReg := 25.U
		}
		is(67108864.U){
			addrReg := 26.U
		}
		is(134217728.U){
			addrReg := 27.U
		}
		is(268435456.U){
			addrReg := 28.U
		}
		is(536870912.U){
			addrReg := 29.U
		}
		is(1073741824.U){
			addrReg := 30.U
		}
	}

   io.slope := bundle1.slope(addrReg)
   io.offset := bundle1.offset(addrReg)

}
