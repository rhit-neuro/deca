# RoCC Accelerators
This is where you should put the `chisel` code for your accelerators. The following two templates along with the included `MultAccelerator` should provide good starting points for making your own accelerator.

### Accelerator Template
Suppose the accelerator you want to make is called `MyAccelerator`. First make a directory for your scala files:
```bash
mkdir myAccelerator
cd myAccelerator
```

Now make a new scala file, `myAccelerator.scala` using the following as a template:
```scala
package myaccelerator

import Chisel._
import freechips.rocketchip.tile._
import freechips.rocketchip.config._

class  MyAccelerator(implicit p: Parameters) extends LazyRoCC {
  override lazy val module = new MyAcceleratorModule(this)
}

class MyAcceleratorModule(outer: MyAccelerator, n: Int = 4)(implicit p: Parameters) extends LazyRoCCModule(outer)
  with HasCoreParameters {
  // Your accelerator code goes here
  // See mult/mult.scala for an example
}
```
#### Things you need to change
  - `package myaccelerator` - Change `myaccelerator` to whatever you want to name the package your accelerator resides in, preferably all lowercase.
  - `MyAccelerator` - Change `MyAccelerator` to whatever you want to name your accelerator. Note, this name is used twice in this template. This name will be used again later in your [AcceleratorConfigs.scala](config/AcceleratorConfigs.scala).
  - `MyAcceleratorModule` - Change `MyAccelerator` whatever you want to name your accelerator and keeep the `Module` suffix. Note, this name is used twice in this tekplate
  - You'll also need to add your accelerator code to the body of class `MyAcceleratorModule`. See [mult.scala](mult/mult.scala) for a working example.

### Accelerator Config Template
Continuing the example for creating `MyAccelerator`, add a configuration in [config/AcceleratorConfigs.scala](config/AcceleratorConfigs.scala) using the following template:
```scala
class MyAcceleratorConfig extends Config(
  new WithMyAccelerator ++ new DefaultConfig)


class WithMyAccelerator extends Config((site, here, up) => {
      case RocketTilesKey => up(RocketTilesKey, site).map { r =>
        r.copy(rocc = Seq(
          RoCCParams(
            opcodes = OpcodeSet.custom0,
            generator = (p: Parameters) => {
              val acc = LazyModule(new myaccelerator.MyAccelerator()(p))
              acc})
          ))
      }
})
```
#### Things you need to change
  - `MyAcceleratorConfig` - Change `MyAccelerator` while keeping the suffix `Config`. This is the name of the config you will use when creating a `ZynqFPGA...` config in [ZynqConfigs.scala](../zynq/ZynqConfigs.scala).
  - `WithMyAccelerator` - Change `MyAccelerator` while keeping the prefix `With`. Note that this name appears twice in the template.
  - `opcodes = OpcodeSet.custom0` - Change `custom0` to match up with the opcode you plan on using for your RoCC instruction that accesses your accelerator (can be `custom0` through `custom3`).
  - `val acc = LazyModule(new myaccelerator.MyAccelerator()(p))` - `acc` can be any name you want. Change `myaccelerator` to the package you declared your accelerator in. Change `MyAccelerator` to the name of your accelerator class.