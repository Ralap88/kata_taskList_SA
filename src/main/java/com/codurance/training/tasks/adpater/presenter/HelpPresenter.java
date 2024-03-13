package com.codurance.training.tasks.adpater.presenter;

import java.io.PrintWriter;

public class HelpPresenter {
    public static void present(PrintWriter out, StringBuilder sb) {
        sb.append("Commands:");
        sb.append("\n");
        sb.append("  show");
        sb.append("\n");
        sb.append("  add project <project name>");
        sb.append("\n");
        sb.append("  add task <project name> <task description>");
        sb.append("\n");
        sb.append("  check <task ID>");
        sb.append("\n");
        sb.append("  uncheck <task ID>");
        sb.append("\n");
        out.printf(sb.toString());
    }
}
