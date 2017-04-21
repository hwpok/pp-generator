#! /bin/sh

CURR_DIR=$(cd `dirname $0`; pwd)
cd $CURR_DIR
echo $CURR_DIR
CLASSPATH=
for C in `ls ../libs/*.jar`; do
    CLASSPATH="$CLASSPATH:$C"
done
CLASSPATH=".:${JAVA_HOME}/bin:${CLASSPATH}"
echo $CLASSPATH
export CLASSPATH
#运行jar文件
java -Dfile.encoding=utf-8 Main sleep 1000 &

exit 0