#!/bin/bash
# Undoes the work done by install-symlinks.sh
# Undo rocket-chip patch
pushd ../fpga-zynq/rocket-chip/
git apply -R ../../patches/*.patch
popd
# Undo symbolic links
rm -f $(pwd)/../fpga-zynq/rocket-chip/src/main/scala/accelerators
rm -f $(pwd)/../fpga-zynq/common/src/main/scala/CustomZynqConfigs.scala