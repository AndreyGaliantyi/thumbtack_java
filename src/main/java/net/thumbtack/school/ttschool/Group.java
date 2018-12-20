package net.thumbtack.school.ttschool;

import java.util.*;

public class Group {
    private String name;
    private String room;
    private List<Trainee> trainees;

    public Group(String name, String room) throws TrainingException {
        if (name == null || name == "") throw new TrainingException(TrainingErrorCode.GROUP_WRONG_NAME);
        if (room == null || room == "") throw new TrainingException(TrainingErrorCode.GROUP_WRONG_ROOM);
        this.name = name;
        this.room = room;
        trainees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws TrainingException {
        if (name == null || name == "") throw new TrainingException(TrainingErrorCode.GROUP_WRONG_NAME);
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) throws TrainingException {
        if (room == null || room == "") throw new TrainingException(TrainingErrorCode.GROUP_WRONG_ROOM);
        this.room = room;
    }

    public List<Trainee> getTrainees() {
        return trainees;
    }

    public void addTrainee(Trainee trainee) {
        trainees.add(trainee);
    }

    public void removeTrainee(Trainee trainee) throws TrainingException {
        if (!trainees.remove(trainee)) throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public void removeTrainee(int index) throws TrainingException {
        try {
            trainees.remove(index);
        } catch (IndexOutOfBoundsException ex) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
    }

    public Trainee getTraineeByFirstName(String firstName) throws TrainingException {
        for (Trainee trainee : trainees) {
            if (trainee.getFirstName().equals(firstName)) return trainee;
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public Trainee getTraineeByFullName(String fullName) throws TrainingException {
        for (Trainee trainee : trainees) {
            if (trainee.getFullName().equals(fullName)) return trainee;
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public void sortTraineeListByFirstNameAscendant() {
        trainees.sort(Comparator.comparing(Trainee::getFirstName));
    }

    public void sortTraineeListByRatingDescendant() {
        trainees.sort(Comparator.comparingInt(Trainee::getRating).reversed());
    }

    public void reverseTraineeList() {
        Collections.reverse(trainees);
    }

    public void rotateTraineeList(int positions) {
        Collections.rotate(trainees, positions);
    }

    public List<Trainee> getTraineesWithMaxRating() throws TrainingException {
        if (trainees.isEmpty()) throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        int maxRating = 0;
        List<Trainee> maxRatingTrainees = new ArrayList<>();
        for (Trainee trainee : trainees) {
            if (trainee.getRating() > maxRating) {
                maxRating = trainee.getRating();
                maxRatingTrainees.clear();
                maxRatingTrainees.add(trainee);
            } else if (trainee.getRating() == maxRating) maxRatingTrainees.add(trainee);
        }
        return maxRatingTrainees;
    }

    public boolean hasDuplicates() {
        HashSet<Trainee> set = new HashSet<>(trainees);
        return trainees.size() > set.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(name, group.name) &&
                Objects.equals(room, group.room) &&
                Objects.equals(trainees, group.trainees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, room, trainees);
    }

}
