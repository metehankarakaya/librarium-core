package com.librarium.core.builder;

import com.librarium.core.app.author.model.AuthorDTO;
import com.librarium.core.app.book.model.BookDTO;

import java.time.LocalDateTime;

public class BookBuilder {

    private final BookDTO bookDTO = new BookDTO();

    public BookBuilder buildDefaultBook() {

        bookDTO.setTitle("Kaşağı");
        bookDTO.setDescription(
                """
                Ömer ve kardeşi Hasan, çocukluklarını büyük bir çiftlikte geçirmişlerdi. Küçük Ömer ve Hasan, en çok atlarla oynamayı, özellikle de onları tımar etmeyi seviyorlardı. Bunun için İstanbul'dan özel getirtilen KAŞAĞI'yı kullanıyorlardı. Ama bir gün Küçük Ömer, çocukluk heyecanıyla KAŞAĞI'yı kırar ve suçu kardeşi Hasan'a atar. Babası bu olaydan sonra Hasan'a çok kızar ve ona:
                -Yalancı, der...
                Hasan, bu olaydan sonra içine kapanır ve bir gün hastalanır. İşte o zaman Ömer için zor günler başlar.
                """
        );
        bookDTO.setLanguage("Türkçe");
        bookDTO.setIsbn("9789752690790");
        bookDTO.setPageCount(93);
        bookDTO.setPublishDate(LocalDateTime.now());
        bookDTO.setPrice(24.50);
        bookDTO.setRating(0.0);

        return this;
    }

    public BookBuilder witTitle(String title) {
        bookDTO.setTitle(title);
        return this;
    }

    public BookBuilder withDescription(String description) {
        bookDTO.setDescription(description);
        return this;
    }

    public BookBuilder withIsbn(String isbn) {
        bookDTO.setIsbn(isbn);
        return this;
    }

    public BookBuilder withPageCount(Integer pageCount) {
        bookDTO.setPageCount(pageCount);
        return this;
    }

    public BookBuilder withPrice(Double price) {
        bookDTO.setPrice(price);
        return this;
    }

    public BookBuilder withCoverImage(byte[] coverImage) {
        bookDTO.setCoverImage(coverImage);
        return this;
    }

    public BookBuilder withAuthor(AuthorDTO authorDTO) {
        bookDTO.setAuthor(authorDTO);
        return this;
    }

    public BookDTO build() {
        return bookDTO;
    }

}
