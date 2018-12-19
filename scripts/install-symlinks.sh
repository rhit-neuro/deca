#!/bin/bash
# "Installs" Custom accelerator and config for custom accelerator into the rocketchip
ln -s $(pwd)/../rocc/rocketchip $(pwd)/../fpga-zynq/rocketchip/custom-accelerator
# "Installs" Config for rocketchip with custom accelerator for zynq boards
ln -s $(pwd)/../rocc/zynq/Configs.scala $(pwd)/../fpga-zynq/common/src/main/scala/CustomConfigs.scala