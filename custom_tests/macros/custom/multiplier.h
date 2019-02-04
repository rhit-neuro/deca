#include "xcustom.h"

#define FUNCT_DO_MULT 0

#define XCUSTOM_ACC 0

#define doMult(rocc_rd, rocc_rs1, rocc_rs2) \
    ROCC_INSTRUCTION(XCUSTOM_ACC, rocc_rd, rocc_rs1, rocc_rs2, FUNCT_DO_MULT)
