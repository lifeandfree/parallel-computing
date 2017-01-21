/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.utils
 *         Дата создания класса: 21 янв. 2017 г.
 */
package ru.urfu.computing.server.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import ru.urfu.computing.server.core.dao.DaoFactory;
import ru.urfu.computing.server.core.logger.Logfile;
import ru.urfu.computing.server.core.model.unhandled.Unhandled;

/**
 * @author lifeandfree
 */
public class FileHandler {

    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler("tags.json");
        fileHandler.writeAllUnhandledElementsToFile(100);
        fileHandler.toString();
    }

    private String fileName;

    public FileHandler() {
        this.fileName = null;
    }

    /**
     *
     */
    public FileHandler(String fileName) {
        this.fileName = fileName;
    }

    public void append(String text) {
        if (this.exists()) {
            this.update(text);
        }
        else {
            this.create();
            this.update(text);
        }
    }

    /**
     * Создать файл.
     *
     * @param fileName
     *            - имя файла.
     */
    public void create() {
        String method = getMethodName() + "(" + fileName != null ? fileName : "null" + "): ";
        try {
            new File(fileName).createNewFile();
        }
        catch (IOException e) {
            Logfile.getInstance().getLogger().error(method + "Could not read file" + e.toString());
        }
        Logfile.getInstance().getLogger().info(method + "File was created");
    }

    /**
     * Проверить существует ли файл.
     *
     * @param fileName
     *            - имя файла.
     */
    public boolean exists() {
        String method = getMethodName() + "(" + fileName != null ? fileName : "null" + "): ";
        File file = new File(fileName);
        if (!file.exists()) {
            Logfile.getInstance().getLogger().error(method + "File not found");
            try {
                throw new FileNotFoundException(file.getName());
            }
            catch (FileNotFoundException e) {
                Logfile.getInstance().getLogger().error(method + "File not found" + e.toString());
                return false;
            }
        }
        else {
            if (!isFile(file)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Получить имя вызвавшего метода
     *
     * @return имя вызвавшего метода
     */
    public String getMethodName() {
        StackTraceElement stack = new Exception().getStackTrace()[1];
        return stack.getClassName() + "." + stack.getMethodName();
    }

    /**
     * Проверить данный объект {@link File} является ли файлом.
     *
     * @param file
     *            {@link File} файл для проверки
     * @return true/false
     */
    public boolean isFile(File file) {
        String method = getMethodName() + "(" + (file != null ? file.getAbsolutePath() : "null") + "): ";
        if (!file.isFile()) {
            Logfile.getInstance().getLogger().error(method + "File is not a file");
            try {
                throw new FileNotFoundException(file.getName());
            }
            catch (FileNotFoundException e) {
                Logfile.getInstance().getLogger().error(method + "File is not a File" + e.toString());
                return false;
            }
        }
        return true;
    }

    /**
     * Прочитать файл построчно.
     *
     * @param fileName
     *            - имя файла.
     * @param endString
     *            - конец строки
     * @return возращает текст из файла.
     */
    public String read(boolean endString) {

        String method = getMethodName() + "(" + (fileName != null ? fileName : "null") + ", "
                + (endString ? "true" : "false") + "): ";

        // Этот спец. объект для построения строки
        StringBuilder sb = new StringBuilder();
        File file = new File(fileName);

        if (!file.exists()) {
            Logfile.getInstance().getLogger().error(method + "File not found");
            return null;
        }
        else {
            try {
                // Объект для чтения файла в буфер
                BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
                try {
                    // В цикле построчно считываем файл
                    String s;
                    while ((s = in.readLine()) != null) {
                        sb.append(s);
                        if (endString) {
                            sb.append("\n");
                        }
                    }
                    Logfile.getInstance().getLogger().info(method + "File was read");
                }
                finally {
                    // Также не забываем закрыть файл
                    in.close();
                }
            }
            catch (IOException e) {
                Logfile.getInstance().getLogger().error(method + "Problems with file output" + e.toString());
                throw new RuntimeException(e);
            }

            // Возвращаем полученный текст с файла
            return sb.toString();
        }
    }

    /**
     * @param fileName
     *            the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Обновить файл.
     *
     * @param fileName
     *            - имя файла.
     * @param newText
     *            - дописываемый текст в файл.
     */
    public void update(String newText) {
        String method = getMethodName() + "(" + (fileName != null ? fileName : "null") + ", "
                + (newText != null ? newText : "null") + "): ";
        if (this.exists()) {
            StringBuilder sb = new StringBuilder();
            String oldFile = read(true);
            sb.append(oldFile);
            sb.append(newText);
            write(sb.toString());
            Logfile.getInstance().getLogger().info(method + "File was updated");
        }
    }

    /**
     * Записать текст в файл.
     *
     * @param fileName
     *            - имя файла.
     * @param text
     *            - дописываемый текст в файл.
     */
    public void write(String text) {
        String method = getMethodName() + "(" + (fileName != null ? fileName : "null") + ", "
                + (text != null ? text : "null") + "): ";
        // Определяем файл
        File file = new File(fileName);
        try {
            // проверяем, что если файл не существует то создаем его
            if (!file.exists()) {
                file.createNewFile();
            }

            // PrintWriter обеспечит возможности записи в файл
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            try {
                // Записываем текст у файл
                out.print(text);
            }
            finally {
                // После чего мы должны закрыть файл
                // Иначе файл не запишется
                out.close();
            }
        }
        catch (IOException e) {
            Logfile.getInstance().getLogger().error(method + "Сould not create a file" + e.toString());
            throw new RuntimeException(e);
        }
    }

    public void writeAllUnhandledElementsToFile(int step) {
        long lengthOfUn = DaoFactory.getInstance().getUnhandledDAO().getSizeOfTable();
        for (int i = 0; i < lengthOfUn + step; i = i + step) {
            writeUnhandledElementsToFileByLength(i, step);
        }
    }

    public void writeUnhandledElementsToFileByLength(int first, int length) {
        FileHandler fileHandler = new FileHandler(fileName);
        StringBuilder sb = new StringBuilder("");
        Collection<Unhandled> Unhandleds = DaoFactory.getInstance().getUnhandledDAO().getLimitElements(first, length);
        for (Unhandled unhandled : Unhandleds) {
            sb.append(unhandled.getTags() + "\n");

        }
        fileHandler.append(sb.toString());

    }
}
