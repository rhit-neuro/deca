#!/bin/bash
# Runs emulator tests

# Exit when any command fails
set -e

# Sets the Config we want to make
CONFIG=LUTROMAcceleratorConfig
ACCELERATOR=LUTROM
TEST=curve_0
CONFIG_UPDATE=false
OUTPUT_FILE="$CONFIG-$TEST-OUTPUT.txt"





# Make sure our changes are in the rocket-chip if needed
if [ "$CONFIG_UPDATE" = "true" ]; then
    echo "Updating the symlinks"
    ./uninstall-symlinks.sh
    ./install-symlinks.sh
fi

cp ../custom_tests/output/$ACCELERATOR-p-$TEST ../fpga-zynq/rocket-chip/emulator

# Make the config if needed
# Run the selected test
pushd ../fpga-zynq/rocket-chip/emulator
if [ "$CONFIG_UPDATE" = true ]; then
    echo "Making the accelerator in the emulator"
    make CONFIG=$CONFIG
fi
echo "Running the test"
./emulator-freechips.rocketchip.system-$CONFIG +verbose $ACCELERATOR-p-$TEST 2> $OUTPUT_FILE
popd
