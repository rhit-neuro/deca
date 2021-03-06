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
    TEST_CASE(2, x10, 0x3e879d0a, )
    li x12, 0xbd9f9485
    ROCC_INSTRUCTION_RAW_R_R_R(CUSTOM_X,11, 12, 13, FUNCT_DO_LUTROM_OFFSET)
    TEST_CASE(3, x11, 0x3cae642c, )
    ROCC_INSTRUCTION_RAW_R_R_R(CUSTOM_X,10, 12, 13, FUNCT_DO_LUTROM_SLOPE)
    TEST_CASE(4, x10, 0x3e879d0a, )
    li x12, 0xbcb890d6
    ROCC_INSTRUCTION_RAW_R_R_R(CUSTOM_X,11, 12, 13, FUNCT_DO_LUTROM_OFFSET)
    TEST_CASE(5, x11, 0x3fae5bc8, )
    ROCC_INSTRUCTION_RAW_R_R_R(CUSTOM_X,10, 12, 13, FUNCT_DO_LUTROM_SLOPE)
    TEST_CASE(6, x10, 0x41e22d0e, )
    li x12, 0x3ca3d70a
    ROCC_INSTRUCTION_RAW_R_R_R(CUSTOM_X,11, 12, 13, FUNCT_DO_LUTROM_OFFSET)
    TEST_CASE(7, x11, 0x3f7fd567, )
    ROCC_INSTRUCTION_RAW_R_R_R(CUSTOM_X,10, 12, 13, FUNCT_DO_LUTROM_SLOPE)
    TEST_CASE(8, x10, 0x0, )
    li x12, 0xbd9f91e6
    ROCC_INSTRUCTION_RAW_R_R_R(CUSTOM_X,11, 12, 13, FUNCT_DO_LUTROM_OFFSET)
    TEST_CASE(9, x11, 0x3cae642c, )
    ROCC_INSTRUCTION_RAW_R_R_R(CUSTOM_X,10, 12, 13, FUNCT_DO_LUTROM_SLOPE)
    TEST_CASE(10, x10, 0x3e879d0a, )
    li x12, 0xbd422681
    ROCC_INSTRUCTION_RAW_R_R_R(CUSTOM_X,11, 12, 13, FUNCT_DO_LUTROM_OFFSET)
    TEST_CASE(11, x11, 0x3f03de2f, )
    ROCC_INSTRUCTION_RAW_R_R_R(CUSTOM_X,10, 12, 13, FUNCT_DO_LUTROM_SLOPE)
    TEST_CASE(12, x10, 0x4119c77a, )
    li x12, 0x3ca328fa
    ROCC_INSTRUCTION_RAW_R_R_R(CUSTOM_X,11, 12, 13, FUNCT_DO_LUTROM_OFFSET)
    TEST_CASE(13, x11, 0x3f7fd567, )
    ROCC_INSTRUCTION_RAW_R_R_R(CUSTOM_X,10, 12, 13, FUNCT_DO_LUTROM_SLOPE)
    TEST_CASE(14, x10, 0x0, )
    li x12, 0x3ca32b13
    ROCC_INSTRUCTION_RAW_R_R_R(CUSTOM_X,11, 12, 13, FUNCT_DO_LUTROM_OFFSET)
    TEST_CASE(15, x11, 0x3f7fd567, )
    ROCC_INSTRUCTION_RAW_R_R_R(CUSTOM_X,10, 12, 13, FUNCT_DO_LUTROM_SLOPE)
    TEST_CASE(16, x10, 0x0, )

    TEST_PASSFAIL

    RVTEST_CODE_END # End of test code

RVTEST_DATA_BEGIN

RVTEST_DATA_END