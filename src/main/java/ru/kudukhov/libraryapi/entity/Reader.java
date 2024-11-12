package ru.kudukhov.libraryapi.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Schema(description = "Reader entity representing a library reader with personal details")
@Entity
public class Reader {

  @Id
  @Schema(description = "Unique phone number identifier of the reader", example = "1234567890")
  private String phoneNumber;

  @Schema(description = "First name of the reader", example = "John")
  private String firstName;

  @Schema(description = "Last name of the reader", example = "Smith")
  private String lastName;

  @Schema(description = "Gender of the reader", example = "Male")
  private String gender;

  @Schema(description = "Birth date of the reader", example = "1990-05-20")
  private LocalDate birthDate;

  public Reader(String phoneNumber, String firstName, String lastName, String gender,
      LocalDate birthDate) {
    this.phoneNumber = phoneNumber;
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
    this.birthDate = birthDate;
  }

  public Reader() {
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Reader reader = (Reader) o;
    return Objects.equals(phoneNumber, reader.phoneNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(phoneNumber);
  }
}
