########################################
########   Project structure:   ########
########################################
protobuf-javame/
				protobuf-javame
				protobuf-javame-examples
				protobuf-javame-midlet-example
				protobuf-javame-multiproject
				protobuf-javame-tests
				
				

########################################
########   Dependency setup:   #########
########################################
Variable:
proto2javameFile = '../protobuf-javame-multiproject/shared-lib/proto2javame-' + protobufVersion + '.jar'

Dependency:
testCompile fileTree(dir: '../protobuf-javame-multiproject/shared-lib/', includes: ['*.jar'])
compile