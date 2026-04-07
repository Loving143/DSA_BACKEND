package com.dsa.manager.repository;

import com.dsa.manager.entity.Question;
import com.dsa.manager.entity.enums.Difficulty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    Page<Question> findByDeletedFalse(Pageable pageable);

    Page<Question> findByTopicIdAndDeletedFalse(Long topicId, Pageable pageable);

    Page<Question> findByDifficultyAndDeletedFalse(Difficulty difficulty, Pageable pageable);

    @Query("""
        SELECT q FROM Question q
        WHERE q.deleted = false
        AND (LOWER(q.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
             OR LOWER(q.description) LIKE LOWER(CONCAT('%', :keyword, '%')))
        """)
    Page<Question> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("""
        SELECT q FROM Question q
        WHERE q.deleted = false
        AND (:topicId IS NULL OR q.topic.id = :topicId)
        AND (:difficulty IS NULL OR q.difficulty = :difficulty)
        AND (:keyword IS NULL OR LOWER(q.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
             OR LOWER(q.description) LIKE LOWER(CONCAT('%', :keyword, '%')))
        """)
    Page<Question> searchQuestions(
        @Param("topicId") Long topicId,
        @Param("difficulty") Difficulty difficulty,
        @Param("keyword") String keyword,
        Pageable pageable
    );
}
