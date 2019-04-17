# Design Accelerator #

This is where you should put and test the chisel code for the accelerators before integrating it with the rocket chip in the [deca](https://ada.csse.rose-hulman.edu/neuroprocessor-group/deca/tree/master) repository. The following template along with the included MultStateAccelerator should provide good starting points for making your own accelerator.

## Creating Accelerator Template ##

Suppose you want to make an accelerator called Accelerator. First create a directory for your scala files:

```bash
mkdir src/main/scala/accelerator
cd src/main/scala/accelerator
```

Now make a new scala file, Accelerator.scala using the following as a template:
```scala
package accelerator

import chisel3._
import chilse
```

## Creating Chisel Tests for Accelerator ##

## Running Chisel Tests for Accelerator ##

