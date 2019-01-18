# Zynq Configs
This is where you can add your accelerator to the `Rocket Chip` generated for your `Zynq` FPGA. All you need to do is add a line to [`ZynqConfigs.scala`](ZynqConfigs.scala) as follows:
```scala
// You will replace YourZynqFPGAConfig and YourAcceleratorConfig
class YourZynqFPGAConfig extends Config(new ZynqFPGAAcceleratorConfig ++ new freechips.rocketchip.system.YourAcceleratorConfig)
```
You should have defined `YourAcceleratorConfig` in [`AcceleratorConfigs.scala`](../accelerators/config/AcceleratorConfigs.scala) and you will use the name `YourZynqFPGAConfig` when you compile the `Rocket Chip` for your FPGA.