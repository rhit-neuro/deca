# DeCA
**DE**veloping **C**omputational **A**rchitectures

## Getting started
### First time setup
First start by cloning this git repository:
```bash
# Use this if you have SSH keys setup with gitlab
git clone git@github.com:rhit-neuro/deca.git
# Otherwise, clone with the https link
git clone https://github.com/rhit-neuro/deca.git
```

#### Development environment
We use Docker to simplify setting up development environments. Follow the instructions in [Setting Up Docker](https://github.com/rhit-neuro/deca-docker/blob/master/README.md#setting-up-docker) to setup Docker. You can stop when you get to the `Using Docker` section.

After installing Docker, follow the instructions in [deca-docker/rocket-chip-env](https://github.com/rhit-neuro/deca-docker/tree/master/rocket-chip-env#running-for-the-first-time) to setup your Docker container for hardware development. These instructions will startup a Docker container with the name `rcenv`. 

Inside your Docker container, run:
```bash
git submodule update --init --recursive
```
This will download all submodules required for development. **You need to do this inside the docker container**.

### Making a new accelerator
Start with the instructions in [sbt/](rocc/sbt/) and [accelerators/](rocc/accelerators/) to make your own accelerator.

Additionally, look at [Multiplier Tutorial](https://github.com/rhit-neuro/deca/wiki/multiplier). It contains an walkthrough for building a custom accelerator.

### Installing and Building your accelerator
See [Building Rocket Chip with your Accelerator](https://github.com/rhit-neuro/deca/wiki/Building-Rocket-Chip-with-your-Accelerator) to learn how to build either the LUTROM acceletator or your own custom accelerator.

### Building Linux
See [Linux](https://github.com/rhit-neuro/deca/wiki/Linux) to learn about how to build the Linux image.

### Putting it all together
See [Put on Zedboard](https://github.com/rhit-neuro/deca/wiki/Put-on-Zedboard) to learn about how to put the multiplier hardware, software, and Linux image on the Zedboard.

## A note on `fpga-images-zedboard` submodule
The `fpga-images-zedboard` within `fpga-zynq` has an out of date `fesvr-zynq` which doesn't let us boot Linux with a disk image. Whenever Josh's merge request [here](https://github.com/ucb-bar/fpga-images-zedboard/pull/3) is accepted, you'll need to update the `fpga-images-zedboard` submodule in [fpga-zynq](https://github.com/ucb-bar/fpga-zynq), submit a merge request, wait for that to be accepted, then update `fpga-zynq` in this repository. After all of that, you can remove the `fpga-images-zedboard` submodule from this repository.
