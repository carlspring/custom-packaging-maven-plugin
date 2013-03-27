
The custom-packaging-maven-plugin is aimed to be used in projects where the packaging is handled
externally, yet you need Maven to install or deploy the artifacts.

This plugin would not normally be useful in fully Mavenized projects, but could alleviate the process
of Mavenizing non-standard (or even -- non-Java) build processes, by providing a means to deploy artifacts
which are already produced by existing (non-Maven) scripts, thus giving you an entry point for using
an artifact repository manager.

The supported project packagings, and, respectively, artifact types are:
- msi
- rar
- rpm
- tar
- tar.bz2
- tar.gz
- tbz
- tgz
- zip
