package net.thumbtack.school.file;

import com.google.gson.Gson;
import net.thumbtack.school.ttschool.Trainee;
import net.thumbtack.school.ttschool.TrainingException;
import net.thumbtack.school.windows.v4.Point;
import net.thumbtack.school.windows.v4.RectButton;
import net.thumbtack.school.windows.v4.base.WindowException;
import net.thumbtack.school.windows.v4.base.WindowState;

import java.io.*;

public class FileService {

    public static void writeByteArrayToBinaryFile(String fileName, byte[] array) throws IOException {
        writeByteArrayToBinaryFile(new File(fileName), array);
    }

    public static void writeByteArrayToBinaryFile(File file, byte[] array) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(array);
        }
    }

    public static byte[] readByteArrayFromBinaryFile(String fileName) throws IOException {
        return readByteArrayFromBinaryFile(new File(fileName));
    }

    public static byte[] readByteArrayFromBinaryFile(File file) throws IOException {
        byte[] bytes;
        try (FileInputStream inputStream = new FileInputStream(file)) {
            bytes = new byte[(int) file.length()];
            inputStream.read(bytes);
        }
        return bytes;
    }

    public static byte[] writeAndReadByteArrayUsingByteStream(byte[] array) throws IOException {
        int length = array.length;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(length);
        outputStream.write(array);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        byte[] bytes;
        if (length % 2 == 0) bytes = new byte[length / 2];
        else bytes = new byte[length / 2 + 1];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) inputStream.read();
            inputStream.skip(1);
        }
        return bytes;
    }

    public static void writeByteArrayToBinaryFileBuffered(String fileName, byte[] array) throws IOException {
        writeByteArrayToBinaryFile(new File(fileName), array);
    }

    public static void writeByteArrayToBinaryFileBuffered(File file, byte[] array) throws IOException {
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(array);
        }
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(String fileName) throws IOException {
        return readByteArrayFromBinaryFileBuffered(new File(fileName));
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(File file) throws IOException {
        byte[] bytes;
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            bytes = new byte[(int) file.length()];
            inputStream.read(bytes);
        }
        return bytes;
    }

    public static void writeRectButtonToBinaryFile(File file, RectButton rectButton) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
            Point topLeft = rectButton.getTopLeft();
            dataOutputStream.writeInt(topLeft.getX());
            dataOutputStream.writeInt(topLeft.getY());

            Point bottomRight = rectButton.getBottomRight();
            dataOutputStream.writeInt(bottomRight.getX());
            dataOutputStream.writeInt(bottomRight.getY());

            dataOutputStream.writeUTF(rectButton.getState().toString());
            dataOutputStream.writeUTF(rectButton.getText());
        }
    }

    public static RectButton readRectButtonFromBinaryFile(File file) throws IOException, WindowException {
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            Point topLeft = new Point(dataInputStream.readInt(), dataInputStream.readInt());
            Point bottomRight = new Point(dataInputStream.readInt(), dataInputStream.readInt());
            String state = dataInputStream.readUTF();
            String text = dataInputStream.readUTF();
            return new RectButton(topLeft, bottomRight, state, text);
        }
    }

    public static void writeRectButtonArrayToBinaryFile(File file, RectButton[] rects) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
            for (RectButton button : rects) {
                Point topLeft = button.getTopLeft();
                dataOutputStream.writeInt(topLeft.getX());
                dataOutputStream.writeInt(topLeft.getY());

                Point bottomRight = button.getBottomRight();
                dataOutputStream.writeInt(bottomRight.getX());
                dataOutputStream.writeInt(bottomRight.getY());
            }
        }
    }

    public static void modifyRectButtonArrayInBinaryFile(File file) throws IOException {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
            int x;
            for (int i = 0; i < file.length() / 8; i++) {
                x = randomAccessFile.readInt();
                randomAccessFile.seek(randomAccessFile.getFilePointer() - 4);
                randomAccessFile.writeInt(++x);
                randomAccessFile.skipBytes(4);
            }
        }
    }

    public static RectButton[] readRectButtonArrayFromBinaryFile(File file) throws IOException, WindowException {
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            RectButton[] rectButtons = new RectButton[(int) file.length() / 16];
            for (int i = 0; i < rectButtons.length; i++) {
                Point topLeft = new Point(dataInputStream.readInt(), dataInputStream.readInt());
                Point bottomRight = new Point(dataInputStream.readInt(), dataInputStream.readInt());
                rectButtons[i] = new RectButton(topLeft, bottomRight, WindowState.ACTIVE, "OK");
            }
            return rectButtons;
        }
    }

    public static void writeRectButtonToTextFileOneLine(File file, RectButton rectButton) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
            Point topLeft = rectButton.getTopLeft();
            dataOutputStream.writeInt(topLeft.getX());
            dataOutputStream.writeByte(' ');
            dataOutputStream.writeInt(topLeft.getY());
            dataOutputStream.writeByte(' ');

            Point bottomRight = rectButton.getBottomRight();
            dataOutputStream.writeInt(bottomRight.getX());
            dataOutputStream.writeByte(' ');
            dataOutputStream.writeInt(bottomRight.getY());
            dataOutputStream.writeByte(' ');

            dataOutputStream.writeUTF(rectButton.getState().toString());
            dataOutputStream.writeByte(' ');
            dataOutputStream.writeUTF(rectButton.getText());
        }
    }

    public static RectButton readRectButtonFromTextFileOneLine(File file) throws IOException, WindowException {
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            Point topLeft = new Point(dataInputStream.readInt(), 0);
            dataInputStream.readByte();
            topLeft.setY(dataInputStream.readInt());
            dataInputStream.readByte();

            Point bottomRight = new Point(dataInputStream.readInt(), 0);
            dataInputStream.readByte();
            bottomRight.setY(dataInputStream.readInt());

            dataInputStream.readByte();
            String state = dataInputStream.readUTF();
            dataInputStream.readByte();
            String text = dataInputStream.readUTF();
            return new RectButton(topLeft, bottomRight, state, text);
        }
    }

    public static void writeRectButtonToTextFileSixLines(File file, RectButton rectButton) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF8"))) {
            Point topLeft = rectButton.getTopLeft();
            writer.write(Integer.toString(topLeft.getX()));
            writer.newLine();
            writer.write(Integer.toString(topLeft.getY()));
            writer.newLine();

            Point bottomRight = rectButton.getBottomRight();
            writer.write(Integer.toString(bottomRight.getX()));
            writer.newLine();
            writer.write(Integer.toString(bottomRight.getY()));
            writer.newLine();

            writer.write(rectButton.getState().toString());
            writer.newLine();
            writer.write(rectButton.getText());
        }
    }

    public static RectButton readRectButtonFromTextFileSixLines(File file) throws IOException, WindowException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"))) {
            Point topLeft = new Point(Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()));
            Point bottomRight = new Point(Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()));
            WindowState state = WindowState.fromString(reader.readLine());
            String text = reader.readLine();
            return new RectButton(topLeft, bottomRight, state, text);
        }
    }

    public static void writeTraineeToTextFileOneLine(File file, Trainee trainee) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
            dataOutputStream.writeUTF(trainee.getFirstName());
            dataOutputStream.writeByte(' ');
            dataOutputStream.writeUTF(trainee.getLastName());
            dataOutputStream.writeByte(' ');
            dataOutputStream.writeInt(trainee.getRating());
        }
    }

    public static Trainee readTraineeFromTextFileOneLine(File file) throws IOException, TrainingException {
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            String firstName = dataInputStream.readUTF();
            dataInputStream.readByte();
            String lastName = dataInputStream.readUTF();
            dataInputStream.readByte();
            int rating = dataInputStream.readInt();
            return new Trainee(firstName, lastName, rating);
        }
    }

    public static void writeTraineeToTextFileThreeLines(File file, Trainee trainee) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF8"))) {
            writer.write(trainee.getFirstName());
            writer.newLine();
            writer.write(trainee.getLastName());
            writer.newLine();
            writer.write(Integer.toString(trainee.getRating()));
        }
    }

    public static Trainee readTraineeFromTextFileThreeLines(File file) throws IOException, TrainingException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"))) {
            String firstName = reader.readLine();
            String lastName = reader.readLine();
            int rating = Integer.parseInt(reader.readLine());
            return new Trainee(firstName, lastName, rating);
        }
    }

    public static void serializeTraineeToBinaryFile(File file, Trainee trainee) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(trainee);
        }
    }

    public static Trainee deserializeTraineeFromBinaryFile(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            return (Trainee) inputStream.readObject();
        }
    }

    public static String serializeTraineeToJsonString(Trainee trainee) {
        Gson gson = new Gson();
        return gson.toJson(trainee);
    }

    public static Trainee deserializeTraineeFromJsonString(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Trainee.class);
    }

    public static void serializeTraineeToJsonFile(File file, Trainee trainee) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            Gson gson = new Gson();
            gson.toJson(trainee, writer);
        }
    }

    public static Trainee deserializeTraineeFromJsonFile(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Gson gson = new Gson();
            return gson.fromJson(reader, Trainee.class);
        }
    }
}
