package com.eventstec.api.services.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.eventstec.api.domain.event.Event;
import com.eventstec.api.domain.event.EventMapper;
import com.eventstec.api.domain.event.EventRequestDTO;
import com.eventstec.api.repositories.EventRepository;
import com.eventstec.api.services.EventService;
import com.eventstec.api.utils.AWSUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;
    @Value("${aws.bucket.name}")
    private String bucketName;
    public EventServiceImpl(final EventMapper eventMapper, final EventRepository eventRepository) {
        this.eventMapper = eventMapper;
        this.eventRepository = eventRepository;
    }
    @Override
    public Event createEvent(EventRequestDTO data) {
        String imgUrl = null;

        if(data.image() != null){
//            imgUrl = this.uploadImg(data.image());
            imgUrl = "c://arquivos/s3images";
        }

        return eventRepository.save(eventMapper.eventRequestDtoForEventEntity(data, imgUrl));
    }

    private String uploadImg(MultipartFile multipartFile) {
        String filename = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        try {
            File file = this.convertMultipartToFile(multipartFile);
            return AWSUtils.sendFileBucketS3(bucketName, filename, file);
        }catch(Exception err){
            System.out.println("Erro ao subir arquivo");
            return null;
        }
    }

    private File convertMultipartToFile(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
