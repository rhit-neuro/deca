package lutrom

import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}
import chisel3.iotesters.PeekPokeTester
import chisel3._

class FMADDTests(c: MulAddFN) extends PeekPokeTester(c)
{
  poke(c.io.a, "h3fc00000".U(32.W)) //1.5
  poke(c.io.b, "h40200000".U(32.W)) //2.5
  poke(c.io.c, "h40600000".U(32.W)) //3.5
  poke(c.io.validin, true.B)
  expect(c.io.validout, false.B)
  step(1)

  poke(c.io.validin, false.B)
  expect(c.io.validout, false.B)
  step(1)

  expect(c.io.validout, true.B)
  expect(c.io.out, "h40e80000".U(32.W)) //7.25
  step(1)

  expect(c.io.validout, false.B)
}

