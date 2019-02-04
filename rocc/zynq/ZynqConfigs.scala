package zynq

import chisel3._
import freechips.rocketchip.config.{Parameters, Config}
import freechips.rocketchip.subsystem._
import freechips.rocketchip.devices.tilelink.BootROMParams
import freechips.rocketchip.rocket.{RocketCoreParams, MulDivParams, DCacheParams, ICacheParams}
import freechips.rocketchip.tile.{RocketTileParams, BuildCore, XLen}
import testchipip._

// This config produces a rocket-chip for a Zynq FPGA using a custom accelerator whose config is MultAcceleratorConfig
// Configs for other accelerators will look similar to this one e.g.
// class YourConfig extends Config(new ZynqFPGAConfig ++ new freechips.rocketchip.system.YourAcceleratorConfig)

// This config produces a rocket-chip for a Zynq FPGA using a custom accelerator whose config is MultAcceleratorConfig
class ZynqFPGAMultAcceleratorConfig extends Config(new ZynqFPGAConfig ++ new freechips.rocketchip.system.MultAcceleratorConfig)

// This config produces a rocket-chip for a Zynq FPGA using a custom accelerator whose config is MultStateAcceleratorConfig
class ZynqFPGAMultStateAcceleratorConfig extends Config(new ZynqFPGAConfig ++ new freechips.rocketchip.system.MultStateAcceleratorConfig)

// This config produces a rocket-chip for a Zynq FPGA using a custom accelerator whose config is LUTROMAcceleratorConfig
class ZynqFPGALUTROMAcceleratorConfig extends Config(new ZynqFPGAConfig ++ new freechips.rocketchip.system.LUTROMAcceleratorConfig)