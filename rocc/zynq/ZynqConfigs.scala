package zynq

import chisel3._
import freechips.rocketchip.config.{Parameters, Config}
import freechips.rocketchip.subsystem._
import freechips.rocketchip.devices.tilelink.BootROMParams
import freechips.rocketchip.rocket.{RocketCoreParams, MulDivParams, DCacheParams, ICacheParams}
import freechips.rocketchip.tile.{RocketTileParams, BuildCore, XLen}
import testchipip._

class AcceleratorConfig extends Config(new WithoutTLMonitors ++ new WithZynqAdapter ++ new WithBootROM)

class ZynqFPGAMultAcceleratorConfig extends Config(new AcceleratorConfig ++ new freechips.rocketchip.system.MultAcceleratorConfig)

