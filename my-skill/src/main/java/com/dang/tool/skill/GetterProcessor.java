package com.dang.tool.skill;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

import com.sun.source.tree.Tree;
import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Names;

// https://blog.mythsman.com/2017/12/19/1/
@SupportedAnnotationTypes("com.dang.tool.skill.Getter") /* 该处理器需要处理的注解 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)        /* 理器支持的源码版本 */
public class GetterProcessor extends AbstractProcessor {

    private Messager messager;      // 主要是用来在编译期打log用的
    private JavacTrees trees;       // 提供了待处理的抽象语法树
    private TreeMaker treeMaker;    // 封装了创建AST节点的一些方法
    private Names names;            // 提供了创建标识符的方法

    /**
     * 主要用途是通过ProcessingEnvironment来获取编译阶段的一些环境信息
     *
     * @param processingEnv 环境信息
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.messager = processingEnv.getMessager();
        this.trees = JavacTrees.instance(processingEnv);
        Context context = ((JavacProcessingEnvironment) processingEnv).getContext();
        this.treeMaker = TreeMaker.instance(context);
        this.names = Names.instance(context);
    }

    /**
     * 要是实现具体逻辑的地方  AST进行处理
     *
     * @param annotations
     * @param roundEnv
     *
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // 利用roundEnv的getElementsAnnotatedWith方法过滤出被Getter这个注解标记的类，并存入set
        Set<? extends Element> set = roundEnv.getElementsAnnotatedWith(Getter.class);
        // 遍历这个set里的每一个元素，并生成jCTree这个语法树
        set.forEach(element -> {
            // 创建一个TreeTranslator，并重写其中的visitClassDef方法，这个方法处理遍历语法树得到的类定义部分jcClassDecl
            JCTree jcTree = trees.getTree(element);
            jcTree.accept(new TreeTranslator() {
                @Override
                public void visitClassDef(JCTree.JCClassDecl jcClassDecl) {
                    // 创建一个jcVariableDeclList保存类的成员变量
                    List<JCTree.JCVariableDecl> jcVariableDeclList = List.nil();
                    // 遍历jcTree的所有成员(包括成员变量和成员函数和构造函数)，过滤出其中的成员变量，并添加进jcVariableDeclList
                    for (JCTree tree : jcClassDecl.defs) {
                        if (tree.getKind().equals(Tree.Kind.VARIABLE)) {
                            JCTree.JCVariableDecl jcVariableDecl = (JCTree.JCVariableDecl) tree;
                            jcVariableDeclList = jcVariableDeclList.append(jcVariableDecl);
                        }
                    }
                    // 将jcVariableDeclList的所有变量转换成需要添加的getter方法，并添加进jcClassDecl的成员中
                    jcVariableDeclList.forEach(jcVariableDecl -> {
                        messager.printMessage(Diagnostic.Kind.NOTE, jcVariableDecl.getName() + " has been processed");
                        // 调用默认的遍历方法遍历处理后的jcClassDecl
                        jcClassDecl.defs = jcClassDecl.defs.prepend(makeGetterMethodDecl(jcVariableDecl));
                    });
                    // 利用上面的TreeTranslator去处理jcTree
                    super.visitClassDef(jcClassDecl);
                }
            });
        });
        return true;
    }

    /**
     * 逻辑就是读取变量的定义，并创建对应的Getter方法，并试图用驼峰命名法。
     *
     * @param jcVariableDecl
     *
     * @return
     */
    private JCTree.JCMethodDecl makeGetterMethodDecl(JCTree.JCVariableDecl jcVariableDecl) {

        ListBuffer<JCTree.JCStatement> statements = new ListBuffer<>();
        statements.append(treeMaker
                .Return(treeMaker.Select(treeMaker.Ident(names.fromString("this")), jcVariableDecl.getName())));
        JCTree.JCBlock body = treeMaker.Block(0, statements.toList());
        return treeMaker.MethodDef(treeMaker.Modifiers(Flags.PUBLIC), getNewMethodName(jcVariableDecl.getName()),
                jcVariableDecl.vartype, List.nil(), List.nil(), List.nil(), body, null);
    }

    private com.sun.tools.javac.util.Name getNewMethodName(Name name) {
        String s = name.toString();
        return names.fromString("get" + s.substring(0, 1).toUpperCase() + s.substring(1, name.length()));
    }
}