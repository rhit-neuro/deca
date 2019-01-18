# Scripts

## [`install-symlinks.sh`](install-symlinks.sh) and [`uninstall-symlinks.sh`](uninstall-symlinks.sh)

### Testing if `install-symlinks.sh` worked
You should see `modified:   src/main/scala/rocket/CSR.scala` as part of `git status`'s output for the following:
```bash
pushd ../fpga-zynq/rocket-chip/
git status
popd
```

You should see `accelerators` appear as a part of `ls`'s output for the following:
```bash
ls ../fpga-zynq/rocket-chip/src/main/scala
```

You should see `CustomZynqConfigs.scala` appear as a part of `ls`'s output for the following:
```bash
ls ../fpga-zynq/common/src/main/scala/
```

### Testing if `uninstall-symlinks.sh` worked
Run the same commands as you did to test if `install-symlinks.sh` worked but check to see that the listed outputs (`modified:   src/main/scala/rocket/CSR.scala`, `accelerators`, and `CustomZynqConfigs.scala`) are missing.