#!/bin/bash
# Builds our LUTROM tests for our custom LUTROM hardware

# Add symbolic links to our LUTROM test assembly file along
# with the necessary header files
ln -s $(pwd)/../verilator-tests/LUTROM $(pwd)/../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/LUTROM
ln -s $(pwd)/../verilator-tests/macros/custom $(pwd)/../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/macros/custom

# Apply a patch to the isa directory to the Makefile to allow us to only
# run our LUTROM tests
# make clean - cleans the directory
# make all - compiles our test program
# undo the patch
pushd ../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/
git apply ../../../../../verilator-tests/LUTROM_tests.patch
make clean
make all
git apply -R ../../../../../verilator-tests/LUTROM_tests.patch
popd

# Make the directory output if it does not already exists
mkdir -p ../verilator-tests/output

# move the LUTROM test files to the output folder if they exist
if [ -f ../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/LUTROM-p-curve_0 ]; then
    mv ../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/LUTROM-p-curve_0 ../verilator-tests/output
fi
if [ -f ../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/LUTROM-v-curve_0 ]; then
    mv ../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/LUTROM-v-curve_0 ../verilator-tests/output
fi

# remove all the dump files from the isa directory
rm -f ../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/*.dump

# Unlink the symbolic links added for our LUTROM test assembly file
rm -f $(pwd)/../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/LUTROM
rm -f  $(pwd)/../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/macros/custom

echo "DONE"
