package zynq

import chisel3._
import freechips.rocketchip.config.{Parameters, Config}
import freechips.rocketchip.subsystem._
import freechips.rocketchip.devices.tilelink.BootROMParams
import freechips.rocketchip.rocket.{RocketCoreParams, MulDivParams, DCacheParams, ICacheParams}
import freechips.rocketchip.tile.{RocketTileParams, BuildCore, XLen}
import testchipip._

class ZynqFPGAAcceleratorConfig extends Config(new WithoutTLMonitors ++ new ZynqAcceleratorConfig)
class ZynqAcceleratorConfig extends Config(new WithZynqAdapter ++ new AcceleratorConfig)
class AcceleratorConfig extends Config(new WithBootROM ++ new freechips.rocketchip.system.CustomAcceleratorConfig)

