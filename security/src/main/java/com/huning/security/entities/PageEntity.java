package com.huning.security.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@Table(name = "pages")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PageEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long pageId;
  private String pageName;
  private String pageUrl;
  private LocalDateTime createDt;

  @JsonIgnore
  @OneToMany(mappedBy = "page", cascade = CascadeType.ALL)
  private List<PageAuthorityEntity> pageAuthorities = new ArrayList<>();

  public void addPageAuthority(PageAuthorityEntity entity) {
    this.pageAuthorities.add(entity);
    entity.setPage(this);
  }
}


