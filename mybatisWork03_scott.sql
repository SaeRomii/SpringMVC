SELECT USER
FROM DUAL;
--==>> SCOTT

--�� �ǽ� ���̺� ����
CREATE TABLE TBL_STUDENT
( SID   NUMBER
, NAME  VARCHAR2(30)
, TEL   VARCHAR2(40)
, CONSTRAINT STUDENT_SID_PK PRIMARY KEY(SID)
);

SELECT * FROM TBL_STUDENT;

--�� ���� ������ �Է�
INSERT INTO TBL_STUDENT(SID, NAME, TEL)
VALUES(2, '�Ҽ���', '010-2222-2222');
INSERT INTO TBL_STUDENT(SID, NAME, TEL)
VALUES(3, '�輭��', '010-3333-3333');
INSERT INTO TBL_STUDENT(SID, NAME, TEL)
VALUES(4, '������', '010-4444-4444');
--==>> 1 �� ��(��) ���ԵǾ����ϴ�. * 3

--�� �ǽ� ���̺� ����
CREATE TABLE TBL_GRADE
( SID   NUMBER
, SUB1  NUMBER(3)
, SUB2  NUMBER(3)
, SUB3  NUMBER(3)
, CONSTRAINT GRADE_SID_PK PRIMARY KEY(SID)
, CONSTRAINT GRADE_SID_FK FOREIGN KEY(SID) REFERENCES TBL_STUDENT(SID)
, CONSTRAINT GRADE_SUB1_CK CHECK(SUB1 BETWEEN 0 AND 100)
, CONSTRAINT GRADE_SUB2_CK CHECK(SUB2 BETWEEN 0 AND 100)
, CONSTRAINT GRADE_SUB3_CK CHECK(SUB3 BETWEEN 0 AND 100)
);
--==>> Table TBL_GRADE��(��) �����Ǿ����ϴ�.

DROP TABLE TBL_GRADE;

SELECT * FROM TBL_GRADE;

--�� ���� ������ �Է�
INSERT INTO TBL_GRADE(SID, SUB1, SUB2, SUB3)
VALUES(1, 90, 80, 70);
INSERT INTO TBL_GRADE(SID, SUB1, SUB2, SUB3)
VALUES(2, 70, 60, 50);
INSERT INTO TBL_GRADE(SID, SUB1, SUB2, SUB3)
VALUES(3, 50, 40, 30);
INSERT INTO TBL_GRADE(SID, SUB1, SUB2, SUB3)
VALUES(4, 90, 90, 30);
--==>> 1 �� ��(��) ���ԵǾ����ϴ�.

--�� Ŀ��
COMMIT;
--==>> Ŀ�Կ�.

--�� �� ����(STUDENTVIEW)
CREATE OR REPLACE VIEW STUDENTVIEW
AS
SELECT SID, NAME, TEL,
    (SELECT COUNT(*)
    FROM TBL_GRADE
    WHERE SID = S.SID) AS SUB
FROM TBL_STUDENT S;
--==>> View STUDENTVIEW��(��) �����Ǿ����ϴ�.

SELECT SID, NAME, TEL, SUB
FROM STUDENTVIEW;
--==>>
/*
1	�̻���	010-1111-1111	1
2	�Ҽ���	010-2222-2222	1
3	�輭��	010-3333-3333	1
4	������	010-4444-4444	1
*/

--�� �� ����(GRADEVIEW)
CREATE OR REPLACE VIEW GRADEVIEW
AS
SELECT S.SID AS SID, S.NAME AS NAME, G.SUB1 AS SUB1, G.SUB2 AS SUB2, G.SUB3 AS SUB3
, (G.SUB1+G.SUB2+G.SUB3) AS TOT
, ((G.SUB1+G.SUB2+G.SUB3)/3) AS AVG
, CASE
    WHEN ((G.SUB1+G.SUB2+G.SUB3)/3)<60 THEN '���հ�'
    WHEN G.SUB1<40 OR G.SUB2<40 OR G.SUB3<40 THEN '����'
    ELSE '�հ�'
    END AS CH
FROM TBL_STUDENT S LEFT JOIN TBL_GRADE G
ON S.SID=G.SID;
--==>> View GRADEVIEW��(��) �����Ǿ����ϴ�.

SELECT SID, NAME, SUB1, SUB2, SUB3, TOT, AVG, CH
FROM GRADEVIEW;
--==>>
/*
1	�̻���	90	80	70	240	80	�հ�
2	�Ҽ���	70	60	50	180	60	�հ�
3	�輭��	50	40	30	120	40	���հ�
4	������	90	90	30	210	70	����
*/













