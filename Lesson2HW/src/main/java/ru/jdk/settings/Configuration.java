package ru.jdk.settings;

import java.util.Objects;

public class Configuration {
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");

    public static String getRepositoryPath(Class myClass) {
        String str = Objects.requireNonNull(
                myClass.getClassLoader().getResource("")).getPath();
        String[] components = str.split("/");
        StringBuilder path = new StringBuilder();
        for (int i = 2; i < components.length - 2; i++) {
            path.append(FILE_SEPARATOR).append(components[i]);
        } // путь до текущего модуля
        path.append(FILE_SEPARATOR).append("src");
        path.append(FILE_SEPARATOR).append("main");
        path.append(FILE_SEPARATOR).append("java");
        String[] packages = myClass.getPackage().getName().split("\\.");
        if (packages.length >= 2) {
            path.append(FILE_SEPARATOR).append(packages[0]);
            path.append(FILE_SEPARATOR).append(packages[1]);
        }
        path.append(FILE_SEPARATOR).append("log").append(FILE_SEPARATOR);
        return path.toString();
    }
}
