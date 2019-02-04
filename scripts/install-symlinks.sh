#!/bin/bash
# Patches rocket-chip to ignore the mstatus_xs bits in order to allow custom
# instructions (e.g. custom0) to run without modifying pk (proxy kernel)
# or a linux kernel
pushd ../fpga-zynq/rocket-chip/
git apply ../../patches/*.patch
popd
# "Installs" Custom accelerator and config for custom accelerator into the rocket-chip
ln -s $(pwd)/../rocc/accelerators $(pwd)/../fpga-zynq/rocket-chip/src/main/scala/accelerators
# "Installs" Config for rocket-chip with custom accelerator for zynq boards
ln -s $(pwd)/../rocc/zynq/ZynqConfigs.scala $(pwd)/../fpga-zynq/common/src/main/scala/CustomZynqConfigs.scala