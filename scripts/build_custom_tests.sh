#!/bin/bash
# Builds our tests that test our custom hardware. 
ln -s $(pwd)/../custom_tests/mult $(pwd)/../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/mult
ln -s $(pwd)/../custom_tests/macros/custom $(pwd)/../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/macros/custom

pushd ../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/
mv Makefile temp-Makefile
popd

ln -s $(pwd)/../custom_tests/Makefile $(pwd)/../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/Makefile

pushd ../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/
make clean
make all 
rm -f  $(pwd)/../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/Makefile
mv temp-Makefile Makefile
popd

mkdir -p output
if [ -f ../fpga-zynq/rocket-chip/riscv-tools/riscv-test/isa/mult-p-doMult ]; then
    mv ../fpga-zynq/rocket-chip/riscv-tools/riscv-test/isa/mult-p-doMult output
fi
if [ -f ../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/mult-v-doMult ]; then
    mv ../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/mult-v-doMult output
fi

rm -f ../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/*.dump

rm -f $(pwd)/../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/mult
rm -f  $(pwd)/../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/macros/custom

echo "DONE"
