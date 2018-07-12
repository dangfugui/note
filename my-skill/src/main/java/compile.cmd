mkdir classes

javac -cp $JAVA_HOME/lib/tools.jar com/dang/tool/skill/Getter* -d classes/

javac -cp classes -d classes -processor com.dang.tool.skill.GetterProcessor com/dang/tool/skill/AppBean.java

javap -p classes com/dang/tool/skill/AppBean.class

java -cp classes com.dang.tool.skill.AppBean