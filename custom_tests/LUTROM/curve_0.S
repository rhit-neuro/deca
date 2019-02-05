#include "riscv_test.h"
#include "riscv_test_rocc.h"
#include "xcustom.h"
#include "test_macros.h"

#define FUNCT_DO_LUTROM_OFFSET 0
#define FUNCT_DO_LUTROM_SLOPE 1

#define CUSTOM_X 0


    RVTEST_WITH_ROCC # Define TVM used by program

start:

    RVTEST_CODE_BEGIN

    li x12, 0xc0a00000
    li x13, 0
    ROCC_INSTRUCTION_RAW_R_R_R(CUSTOM_X,11, 12, 13, FUNCT_DO_LUTROM_OFFSET)
    TEST_CASE(1, x11, 0x3cae642c, )
    ROCC_INSTRUCTION_RAW_R_R_R(CUSTOM_X,10, 12, 13, FUNCT_DO_LUTROM_SLOPE)
    TEST_CASE(2, x10,0x3e879d0a, )

    TEST_PASSFAIL

    RVTEST_CODE_END # End of test code

RVTEST_DATA_BEGIN

RVTEST_DATA_END