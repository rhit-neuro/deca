#!/bin/bash
# Builds our multiplier tests for our custom multiplier hardware

# Add symbolic links to our multiplier test assembly file along
# with the necessary header files
ln -s $(pwd)/../custom_tests/mult $(pwd)/../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/mult
ln -s $(pwd)/../custom_tests/macros/custom $(pwd)/../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/macros/custom

# Apply a patch to the isa directory to the Makefile to allow us to only
# run our multiplier tests
# make clean - cleans the directory
# make all - compiles our test program
# undo the patch
pushd ../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/
git apply ../../../../../custom_tests/mult_tests.patch
make clean
make all
git apply -R ../../../../../custom_tests/mult_tests.patch
popd

# Make the directory output if it does not already exists
mkdir -p ../custom_tests/output

# move the multiplier test files to the output folder if they exist
if [ -f ../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/mult-p-doMult ]; then
    mv ../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/mult-p-doMult ../custom_tests/output
fi
if [ -f ../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/mult-v-doMult ]; then
    mv ../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/mult-v-doMult ../custom_tests/output
fi

# remove all the dump files from the isa directory
rm -f ../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/*.dump

# Unlink the symbolic links added for our multiplier test assembly file
rm -f $(pwd)/../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/mult
rm -f  $(pwd)/../fpga-zynq/rocket-chip/riscv-tools/riscv-tests/isa/macros/custom

echo "DONE"
