package com.viva.live.logger;

import android.content.Context;
import android.text.TextUtils;

import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.elvishew.xlog.flattener.ClassicFlattener;
import com.elvishew.xlog.printer.AndroidPrinter;
import com.elvishew.xlog.printer.Printer;
import com.elvishew.xlog.printer.file.FilePrinter;
import com.elvishew.xlog.printer.file.backup.NeverBackupStrategy;
import com.elvishew.xlog.printer.file.clean.FileLastModifiedCleanStrategy;
import com.elvishew.xlog.printer.file.naming.DateFileNameGenerator;
import com.viva.live.service.log.ILogerService;

/**
 * author:mingming.liu
 * description:日志服务实现者
 * warn:
 * time: 2019-09-06
 */
public class LoggerServiceImpl implements ILogerService {
    private static final String sFormatModule = "Module is %s, Tag is %s, Message is %s";

    boolean enable = true;

    @Override
    public void init(Context context, String filePath) {
        LogConfiguration config = new LogConfiguration.Builder()
                .logLevel(BuildConfig.DEBUG ? LogLevel.ALL : LogLevel.NONE)                 // Add a log interceptor
                .build();

        Printer androidPrinter = new AndroidPrinter();             // Printer that print the log using android.util.Log
        if (!TextUtils.isEmpty(filePath)) {
            Printer filePrinter = new FilePrinter                      // Printer that print the log to the file system
                    .Builder(filePath)                              // Specify the path to save log file
                    .fileNameGenerator(new DateFileNameGenerator())        // Default: ChangelessFileNameGenerator("log")
                    .backupStrategy(new NeverBackupStrategy())             // Default: FileSizeBackupStrategy(1024 * 1024)
                    .cleanStrategy(new FileLastModifiedCleanStrategy(Long.MAX_VALUE))
                    .flattener(new ClassicFlattener())  // Default: DefaultFlattener
                    .build();
            XLog.init(config, androidPrinter,
                    filePrinter);
        } else {
            XLog.init(config, androidPrinter);
        }
    }

    @Override
    public void enable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public void i(String module, String tag, String message) {
        if (enable) {
            XLog.i(sFormatModule, module, tag, message);
        }
    }

    @Override
    public void d(String module, String tag, String message) {
        if (enable) {
            XLog.d(sFormatModule, module, tag, message);
        }
    }

    @Override
    public void e(String module, String tag, String message) {
        if (enable) {
            XLog.e(sFormatModule, module, tag, message);
        }
    }

    @Override
    public void e(String module, String tag, String format, Object... args) {
        if (enable) {
            String message = String.format(format, args);
            XLog.e(sFormatModule, module, tag, message);
        }
    }

    @Override
    public void e(String module, String msg, Throwable throwable) {
        if (enable) {
            XLog.e(msg, throwable);
        }
    }

    @Override
    public void release() {

    }
}
