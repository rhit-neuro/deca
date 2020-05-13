package lutrom

import chisel3._
import chisel3.util._
import hardfloat._

// Essentially the FPU MulAddRecFNPipe from RocketChip
class MulAddFN extends Module
{
	val io = IO(new Bundle {
      val validin = Input(Bool())
      val a = Input(UInt(32.W))
      val b = Input(UInt(32.W))
      val c = Input(UInt(32.W))
      val out = Output(UInt(33.W))
      val validout = Output(Bool())
   })

	val mulAddRecFNToRaw_preMul =
        Module(new MulAddRecFNToRaw_preMul(8, 24))
    val mulAddRecFNToRaw_postMul =
        Module(new MulAddRecFNToRaw_postMul(8, 24))
    val roundRawFNToRecFN =
    	Module(new RoundRawFNToRecFN(8, 24, 0))

    mulAddRecFNToRaw_preMul.io.op := 0.U // a*b + c, no negation
    mulAddRecFNToRaw_preMul.io.a  := recFNFromFN(8, 24, io.a)
    mulAddRecFNToRaw_preMul.io.b  := recFNFromFN(8, 24, io.b)
    mulAddRecFNToRaw_preMul.io.c  := recFNFromFN(8, 24, io.c)

    val mulAddResult =
        (mulAddRecFNToRaw_preMul.io.mulAddA *
             mulAddRecFNToRaw_preMul.io.mulAddB) +&
            mulAddRecFNToRaw_preMul.io.mulAddC

    val valid_stage0 = Wire(Bool())
    val roundingMode_stage0 = Wire(UInt(3.W))
    val detectTininess_stage0 = Wire(UInt(1.W))

  	// A Pipe with this constructor delays the (valid, data) pair passed to it by one cycle
  	// The Pipe().valid is false and the Pipe().bits is 0 until the pipeline is filled after a TRUE valid is provided as input.
    mulAddRecFNToRaw_postMul.io.fromPreMul   := Pipe(io.validin, mulAddRecFNToRaw_preMul.io.toPostMul).bits
    mulAddRecFNToRaw_postMul.io.mulAddResult := Pipe(io.validin, mulAddResult).bits
    mulAddRecFNToRaw_postMul.io.roundingMode := Pipe(io.validin, consts.round_near_even).bits
    roundingMode_stage0                      := Pipe(io.validin, consts.round_near_even).bits
    detectTininess_stage0                    := Pipe(io.validin, consts.tininess_afterRounding).bits
    valid_stage0                             := Pipe(io.validin, false.B).valid
    
    roundRawFNToRecFN.io.invalidExc         := Pipe(valid_stage0, mulAddRecFNToRaw_postMul.io.invalidExc).bits
    roundRawFNToRecFN.io.infiniteExc        := false.B
    roundRawFNToRecFN.io.in                 := Pipe(valid_stage0, mulAddRecFNToRaw_postMul.io.rawOut).bits
    roundRawFNToRecFN.io.roundingMode       := Pipe(valid_stage0, roundingMode_stage0).bits
    roundRawFNToRecFN.io.detectTininess     := Pipe(valid_stage0, detectTininess_stage0).bits
    io.validout                             := Pipe(valid_stage0, false.B).valid

    io.out := fNFromRecFN(8, 24, roundRawFNToRecFN.io.out)
}
