package com.rubypapar;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.rubypapar.domain.Board;
import com.rubypapar.persistence.BoardRepository;

@Component
public class DataInit implements ApplicationRunner {
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Random rd = new Random();
		for(int i=1; i<10; i++) {
			boardRepo.save(Board.builder()
					.title("title "+i)
					.writer("writer")
					.content("content "+ i)
					.createDate(new Date())
					.cnt(rd.nextLong(100))
					.build());
		}
	}

}
