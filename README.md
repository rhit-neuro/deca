# DECA
**DE**signing **C**omputational **A**rchitectures

## Setting up your environment
We highly recommend using docker for your development environment.
Follow the instructions in [rocket-chip-env](https://ada.csse.rose-hulman.edu/neuroprocessor-group/parallel-neuro-simulation/blob/master/docker/rocket-chip-env/) for setting up the docker image associated with this project.

## Getting started
#### First time setup
After cloning this repo, execute the following to download the `chisel` code for building a `rocket-chip`:
```bash
# It is critical you run this in your docker container if you're using docker
git submodule update --init --recursive
```
Now executing the following will "install" custom accelerators into the `rocket-chip`:
```bash
# This only needs to be done one time after first cloning this repo
# This can be done before you make any accelerators
cd scripts
./install-symlinks.sh
cd ..
```
#### Making a new accelerator
Start with the instructions in [accelerators/](rocc/accelerators/) to make your own accelerator.
#### Building a Rocket Chip
When you're ready to build a new `rocket-chip` with your accelerator, run the following commands:
```bash
make rocket CONFIG=ZynqFPGAMyAcceleratorConfig ROCKETCHIP_ADDONS=accelerators
make project CONFIG=ZynqFPGAMyAcceleratorConfig ROCKETCHIP_ADDONS=accelerators
make CONFIG=ZynqFPGAMyAcceleratorConfig ROCKETCHIP_ADDONS=accelerators
```
When you make changes to your accelerator, just rerun the above 3 `make` commands.