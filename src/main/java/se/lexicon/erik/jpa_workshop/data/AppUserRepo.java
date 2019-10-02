package se.lexicon.erik.jpa_workshop.data;

import org.springframework.data.repository.CrudRepository;

import se.lexicon.erik.jpa_workshop.entity.AppUser;

public interface AppUserRepo extends CrudRepository<AppUser, Integer>{

}
