# DeCA
**DE**veloping **C**omputational **A**rchitectures

## Setting up your environment
We highly recommend using docker for your development environment.
Follow the instructions in [rocket-chip-env](https://github.com/rhit-neuro/deca-docker/tree/master/rocket-chip-env) for setting up the docker image associated with this project.

## Getting started
#### First time setup
After cloning this repo, execute the following to download the `chisel` code for building a `rocket-chip`:
```bash
# It is critical you run this in your docker container if you're using docker
git submodule update --init --recursive
```
#### Making a new accelerator
Start with the instructions in [sbt/] (rocc/sbt/) and [accelerators/](rocc/accelerators/) to make your own accelerator.
#### Installing your accelerator
Now executing the following will "install" custom accelerators into the `rocket-chip`:
```bash
cd scripts
./install-symlinks.sh
cd ..
```
If you make changes to your acclerators, you'll need to "reinstall" your accelerators with:
```bash
cd scripts
./uininstall-symlinks.sh
./install-symlinks.sh
cd ..
```
#### Building a Rocket Chip
When you're ready to build a new `rocket-chip` with your accelerator, run the following commands:
```bash
# replace ZynqFPGAMyAcceleratorConfig with the name of your config
# if you followed the style in the README's, you'll just replace
# MyAccelerator with the name of your accelerator
make rocket CONFIG=ZynqFPGAMyAcceleratorConfig ROCKETCHIP_ADDONS=accelerators
make project CONFIG=ZynqFPGAMyAcceleratorConfig ROCKETCHIP_ADDONS=accelerators
make CONFIG=ZynqFPGAMyAcceleratorConfig ROCKETCHIP_ADDONS=accelerators
```
When you make changes to your accelerator, remember to "reinstall" your accelerator as mentioned in the previous section and then rerun the above 3 `make` commands.


#### Building Linux
In order to change what events the Rocket Chip's hardware performance monitors count, you must rebuild linux. The following will build linux with our default hardware performance monitors enabled.
```bash
cd firesim-software
git submodule update --init
cd riscv-pk
git apply ../../patches/riscv-pk/bbl-perf-counters.patch
cd ..
./sw-manager -c br-disk.json build
```
This will compile Linux with your modified bootloader. This will create two files in the `images` directory.
- `br-disk-bin` - This binary is the bootloader bundled with your RISC-V Linux kernel as its payload.
- `br-disk.img` - This is your ext2 root filesystem for Linux.

If you want to change what events are counted, edit `firesim-software/riscv-pk/machine/minit.c` then change to the `firesim-software` directory and run:
```bash
./sw-manager -c br-disk.json build
```

## A note on `fpga-images-zedboard` submodule
The `fpga-images-zedboard` within `fpga-zynq` has an out of date `fesvr-zynq` which doesn't let us boot Linux with a disk image. Whenever Josh's merge request [here](https://github.com/ucb-bar/fpga-images-zedboard/pull/3) is accepted, you'll need to update the `fpga-images-zedboard` submodule in [fpga-zynq](https://github.com/ucb-bar/fpga-zynq), submit a merge request, wait for that to be accepted, then update `fpga-zynq` in this repository. After all of that, you can remove the `fpga-images-zedboard` submodule from this repository.