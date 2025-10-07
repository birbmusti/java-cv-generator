import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import java.io.File;

public class PdfResumeGenerator {
    public static void main(String[] args) {
        try {
            PDDocument document = new PDDocument(); // Boş PDF oluştur
            PDPage page = new PDPage(PDRectangle.A4); // A4 sayfa oluştur
            document.addPage(page); // Sayfayı ekle

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // 🔹 1️⃣ Fotoğrafı yükle
            PDImageXObject photo = PDImageXObject.createFromFile("kopekai.png", document);

            // 🔹 2️⃣ Fotoğrafı çiz (x=400, y=680 civarı sağ üst köşe olur)
            contentStream.drawImage(photo, 400, 680, 120, 120);
            // (120x120 = genişlik ve yükseklik; istediğin gibi değiştirebilirsin)

            // 🔹 3️⃣ Fontu yükle
            PDType0Font font = PDType0Font.load(document, new File("C:/Windows/Fonts/arial.ttf"));

            // 🔹 4️⃣ Yazı yaz
            contentStream.beginText();
            contentStream.setFont(font, 14);
            contentStream.newLineAtOffset(50, 750);
            contentStream.showText("ÖZGEÇMİŞ");
            contentStream.newLineAtOffset(0, -25);
            contentStream.showText("Ad Soyad: Ahmet Yılmaz");
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Telefon: 0555 111 22 33");
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("E-posta: ahmetyilmaz@example.com");
            contentStream.newLineAtOffset(0, -40);
            contentStream.showText("İş Deneyimleri:");
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("- Yazılım Stajyeri, Pear Teknoloji (2020-2021)");
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("- Backend Developer, Banana Ltd. (2021-2023)");
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("- Kıdemli Java Geliştirici, KodX (2023-2025)");
            contentStream.endText();

            contentStream.close();
            document.save("ozgecmis.pdf");
            document.close();

            System.out.println("PDF başarıyla oluşturuldu (fotoğraf eklendi)!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
