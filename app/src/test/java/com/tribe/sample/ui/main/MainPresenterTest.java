package com.tribe.sample.ui.main;

import com.tribe.sample.data.MovieRepository;
import com.tribe.sample.data.models.Movie;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    MovieRepository repository;

    @Mock
    MainContract.MainView mainView;

    MainPresenter mainPresenter;

    @Before
    public void setUp() throws Exception {
        mainPresenter = new MainPresenter(mainView, repository);
    }

    @Test
    public void initialize_State_ExpectedBehaviour() {
        //Given
        final List<Movie> movies = new ArrayList<>();

        //When
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((MovieRepository.MovieListListener)invocation.getArguments()[0]).onMovieListReceived(movies);
                return null;
            }
        }).when(repository).getMovieList(mainPresenter);

        mainPresenter.initialize();

        //Then
        Mockito.verify(mainView).renderMovieList(movies);
    }
}