package pl.polsl.paweljaneta.databasebenchmark.dataInsertion.utils;

import org.springframework.beans.factory.annotation.Autowired;

//@Component
public class IdGenerator {
   /* @Autowired
    private LastIndexRepository lastIndexRepository;*/

    private Long id = 0L;
  //  private LastId lastId;
    private boolean firstRun = true;

    public Long getId() {
       /* if (firstRun) {
            getPersistedId();
            firstRun = false;
        }*/

        Long result = id;
        id++;
      /*  lastId.setId(id);
        lastIndexRepository.save(lastId);*/
        return result;
    }

    public void setStartId(Long id) {
        this.id = id;
    }

  /*  private void getPersistedId() {
        if (id == 0) {
            List<LastId> listIds = lastIndexRepository.findAll();
            if (listIds.size() > 0) {
                lastId = listIds.get(0);
                id = lastId.getId();
            } else {
                lastId = new LastId();
                id = 0L;
            }
        }
    }*/
}
